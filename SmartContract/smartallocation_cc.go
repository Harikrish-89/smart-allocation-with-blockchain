package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// SmartAllocationChaincode implementation
type SmartAllocationChaincode struct {
}

type allocationModel struct {
	RequestID             string `json:"requestId"`
	AllocationID          string `json:"allocationId"`
	Status                string `json:"status"`
	ClientOrg             string `json:"clientOrg"`
	ClientID              string `json:"clientId"`
	ParentPortfolio       string `json:"parentPortfolio"`
	ChildPortfolio        string `json:"childPortfolio"`
	Tolerances            string `json:"tolerances"`
	TargetWeight          string `json:"targetWeight"`
	TargetWeightTolerance string `json:"targetWeightTolerance"`
	ShareClassConfigFlag  bool   `json:"shareClassConfigFlag"`
	InvstFrozenConfig     string `json:"invstFrozenConfig"`
	TargetCash            string `json:"targetCash"`
	OverdraftLimit        string `json:"overdraftLimit"`
	MinimumTradeSize      int    `json:"minimumTradeSize"`
}

// ===================================================================================
//  init...initializes chaincode
// ===================================================================================
func (t *SmartAllocationChaincode) Init(stub shim.ChaincodeStubInterface) pb.Response {

	fmt.Println("Init is running ")
	//To-do This should load from mock service

	initAllocations(stub)

	return shim.Success(nil)
}

func initAllocations(stub shim.ChaincodeStubInterface) pb.Response {

	log.Println("initAllocations loading default allocations ")

	//To-do This should load from mock service
	//url := "http://10.0.75.1:7080/allocation/all"
	url := "http://smartallocationapp.herokuapp.com/allocation/all"

	// Build the request
	req, err := http.NewRequest("GET", url, nil)

	if err != nil {
		log.Fatal("Failed constructing request to get data from SmartAllocationApp: ", err)

	}

	// For control over HTTP client headers,
	// redirect policy, and other settings,
	// create a Client
	// A Client is an HTTP client
	client := &http.Client{}

	// Send the request via a client
	// Do sends an HTTP request and
	// returns an HTTP response
	resp, err := client.Do(req)
	if err != nil {
		log.Fatal("Failed fetching data from SmartAllocationApp: : ", err)
	}

	// Callers should close resp.Body
	// when done reading from it
	// Defer the closing of the body
	defer resp.Body.Close()

	allocationModels := []allocationModel{}

	// Use json.Decode for reading streams of JSON data
	if err := json.NewDecoder(resp.Body).Decode(&allocationModels); err != nil {
		log.Fatal("Failed parsing data from SmartAllocationApp: : ", err)
	}

	i := 0
	for i < len(allocationModels) {
		log.Println("i is ", i)
		allocationModelAsBytes, _ := json.Marshal(allocationModels[i])
		if allocationModels[i].Status == "Pending" {
			stub.PutState(allocationModels[i].RequestID, allocationModelAsBytes)
		} else {
			stub.PutState(allocationModels[i].AllocationID, allocationModelAsBytes)
		}
		log.Println("Added", allocationModels[i])
		i = i + 1
	}

	return shim.Success(nil)
}

// ===================================================================================
// Invoke - Our entry point for Invocations
// ===================================================================================
func (t *SmartAllocationChaincode) Invoke(stub shim.ChaincodeStubInterface) pb.Response {

	function, args := stub.GetFunctionAndParameters()
	log.Println("invoke is running " + function)

	// Handle different functions
	if function == "getAllAllocations" { //Fetch all allocation changes for Auditor
		return t.getAllAllocations(stub, args)
	} else if function == "getLatestAllocation" { //Fetch latest approved allocation for client
		return t.getLatestAllocation(stub, args)
	} else if function == "getPendingAllocations" { //Fetch all pending allocation changes for Reviewer
		return t.getPendingAllocations(stub, args)
	} else if function == "reviewAllocationChange" { //Update the review outcome of allocation changes
		return t.reviewAllocationChange(stub, args)
	} else if function == "modifyAllocation" { //Process allocation change request
		return t.modifyAllocation(stub, args)
	}

	log.Println("invoke did not find func: " + function) //error
	return shim.Error("Received unknown function invocation")
}

// ===============================================================================================
// getLatestAllocation - Loads the current allocation state of the network based on the user org
// ================================================================================================
func (t *SmartAllocationChaincode) getLatestAllocation(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	// ==== Input sanitation ====
	log.Println("- start getLatestAllocation")

	var err error

	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting User Role & Org")
	}

	if args[0] != "Client" {
		return shim.Error("User does not have access to perform this operation")
	}

	clientAllocationModel, err := stub.GetState(args[1])

	if err != nil {
		return shim.Error(err.Error())
	}

	if clientAllocationModel == nil {
		return shim.Error("No AllocationModel Found")
	}

	return shim.Success(clientAllocationModel)
}

// =============================================================================================================
// getPendingAllocations - Loads all the allocation change request pending for approval based on the user role
//=============================================================================================================
func (t *SmartAllocationChaincode) getPendingAllocations(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	// ==== Input sanitation ====
	log.Println("- start getPendingAllocations")

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting User Role")
	}

	if args[0] != "Approver" {
		return shim.Error("User does not have access to perform this operation")
	}

	resultsIterator, err := stub.GetStateByRange("", "")
	if err != nil {
		return shim.Error(err.Error())
	}
	defer resultsIterator.Close()

	// buffer is a JSON array containing QueryResults
	var buffer bytes.Buffer
	buffer.WriteString("[")

	bArrayMemberAlreadyWritten := false
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}

		allocationModelJSON := allocationModel{}
		err = json.Unmarshal(queryResponse.Value, &allocationModelJSON)
		if err == nil {
			if allocationModelJSON.Status == "Pending" {
				// Add a comma before array members, suppress it for the first array member
				if bArrayMemberAlreadyWritten == true {
					buffer.WriteString(",")
				}
				buffer.WriteString("{\"Key\":")
				buffer.WriteString("\"")
				buffer.WriteString(queryResponse.Key)
				buffer.WriteString("\"")

				buffer.WriteString(", \"Record\":")
				// Record is a JSON object, so we write as-is
				buffer.WriteString(string(queryResponse.Value))
				buffer.WriteString("}")
				bArrayMemberAlreadyWritten = true
			}
		}

	}
	buffer.WriteString("]")

	log.Printf("- getPendingAllocations:\n%s\n", buffer.String())
	return shim.Success(buffer.Bytes())
}

// =============================================================================================================
// getAllAllocations - Loads all the ledger details of the network based on the user role
//=============================================================================================================
func (t *SmartAllocationChaincode) getAllAllocations(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	// ==== Input sanitation ====
	log.Println("- start getAllAllocations")

	var err error

	if len(args) != 1 {
		return shim.Error("Incorrect number of arguments. Expecting User role")
	}

	if args[0] != "Auditor" {
		return shim.Error("User does not have access to perform this operation")
	}

	resultsIterator, err := stub.GetStateByRange("", "")
	if err != nil {
		return shim.Error(err.Error())
	}
	defer resultsIterator.Close()

	// buffer is a JSON array containing QueryResults
	var buffer bytes.Buffer
	buffer.WriteString("[")

	bArrayMemberAlreadyWritten := false
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return shim.Error(err.Error())
		}
		// Add a comma before array members, suppress it for the first array member
		if bArrayMemberAlreadyWritten == true {
			buffer.WriteString(",")
		}
		buffer.WriteString("{\"Key\":")
		buffer.WriteString("\"")
		buffer.WriteString(queryResponse.Key)
		buffer.WriteString("\"")

		buffer.WriteString(", \"Record\":")
		// Record is a JSON object, so we write as-is
		buffer.WriteString(string(queryResponse.Value))
		buffer.WriteString("}")
		bArrayMemberAlreadyWritten = true
	}
	buffer.WriteString("]")

	log.Printf("- getAllAllocations:\n%s\n", buffer.String())
	return shim.Success(buffer.Bytes())
}

// =============================================================================================================
// modifyAllocation - Initiate a request for modifying the allocation model based on the user role
//=============================================================================================================
func (t *SmartAllocationChaincode) modifyAllocation(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	// ==== Input sanitation ====
	log.Println("- start modifyAllocation")
	log.Println(args)
	var err error

	if len(args) != 2 {
		return shim.Error("Incorrect number of arguments. Expecting 2")
	}

	if args[0] != "Client" {
		return shim.Error("User does not have access to perform this operation")
	}

	allocationChangeRequest := allocationModel{}
	json.Unmarshal([]byte(args[1]), &allocationChangeRequest)

	allocationChangeRequestAsBytes, err := json.Marshal(allocationChangeRequest)
	if err != nil {
		return shim.Error(fmt.Sprintf("Failed to modifyAllocation: %s", args[1]))
	}

	err = stub.PutState(allocationChangeRequest.RequestID, allocationChangeRequestAsBytes)

	if err != nil {
		return shim.Error(fmt.Sprintf("Failed to modifyAllocation: %s", args[1]))
	}

	return shim.Success(nil)
}

// =============================================================================================================
// reviewAllocationChange - Review a pending allocation change and approve/reject allocation change
//=============================================================================================================
func (t *SmartAllocationChaincode) reviewAllocationChange(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	// ==== Input sanitation ====
	fmt.Println("- start reviewAllocationChange")

	if len(args) != 3 {
		return shim.Error("Incorrect number of arguments. Expecting User role,RequestId,Status")
	}

	if args[0] != "Approver" {
		return shim.Error("User does not have access to perform this operation")
	}

	pendingAllocationModelAsBytes, _ := stub.GetState(args[1])

	if pendingAllocationModelAsBytes == nil {
		return shim.Error("Could not location pending allocation" + string(pendingAllocationModelAsBytes))
	}

	allocationModel := allocationModel{}

	json.Unmarshal(pendingAllocationModelAsBytes, &allocationModel)

	allocationModel.Status = args[2]
	allocationModelAsBytes, _ := json.Marshal(allocationModel)

	stub.PutState(args[1], allocationModelAsBytes)

	if allocationModel.Status == "Approved" {

		//To-do This should update mock service
		url := "http://smartallocationapp.herokuapp.com/allocation/modifyallocation"

		// Build the request
		req, err := http.NewRequest("POST", url, bytes.NewBuffer(allocationModelAsBytes))
		req.Header.Set("Accept", "application/json")
		req.Header.Set("Content-Type", "application/json")

		if err != nil {
			log.Fatal("Failed constructing modify allocation request for SmartAllocationApp: ", err)
			shim.Error(fmt.Sprintf("Failed to modifyAllocation: %s", args[1]))

		}

		// For control over HTTP client headers,
		// redirect policy, and other settings,
		// create a Client
		// A Client is an HTTP client
		client := &http.Client{}

		// Send the request via a client
		// Do sends an HTTP request and
		// returns an HTTP response
		resp, err := client.Do(req)
		if err != nil {
			log.Fatal("Failed processing modify allocation reequest to SmartAllocationApp: : ", err)
			return shim.Error(fmt.Sprintf("Failed to modifyAllocation: %s", args[1]))
		}

		// Callers should close resp.Body
		// when done reading from it
		// Defer the closing of the body
		defer resp.Body.Close()

		stub.PutState(allocationModel.ClientOrg, allocationModelAsBytes)
		//stub.DelState(args[1])
	}

	return shim.Success(nil)
}

// =========================================================================================
// getQueryResultForQueryString executes the passed in query string.
// Result set is built and returned as a byte array containing the JSON results.
// =========================================================================================
func getQueryResultForQueryString(stub shim.ChaincodeStubInterface, queryString string) ([]byte, error) {

	log.Printf("- getQueryResultForQueryString queryString:\n%s\n", queryString)

	resultsIterator, err := stub.GetQueryResult(queryString)
	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	// buffer is a JSON array containing QueryRecords
	var buffer bytes.Buffer
	buffer.WriteString("[")

	bArrayMemberAlreadyWritten := false
	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()
		if err != nil {
			return nil, err
		}
		// Add a comma before array members, suppress it for the first array member
		if bArrayMemberAlreadyWritten == true {
			buffer.WriteString(",")
		}
		buffer.WriteString(string(queryResponse.Value))
		bArrayMemberAlreadyWritten = true
	}
	buffer.WriteString("]")

	log.Printf("- getQueryResultForQueryString queryResult:\n%s\n", buffer.String())

	return buffer.Bytes(), nil
}

// main function starts up the chaincode in the container during instantiate
func main() {
	if err := shim.Start(new(SmartAllocationChaincode)); err != nil {
		log.Printf("Error starting SmartAllocationChaincode: %s", err)
	}
}
