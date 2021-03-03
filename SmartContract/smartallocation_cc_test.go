package main

import (
	"encoding/json"
	"fmt"
	"testing"

	"github.com/hyperledger/fabric/core/chaincode/shim"
)

func TestGetAllAllocationModels(t *testing.T) {
	fmt.Println("Entering TestGetAllAllocationModels")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	role := "Auditor"

	result := stub.MockInvoke("t123", [][]byte{[]byte("getAllAllocations"), []byte(role)})

	fmt.Println("Response status:", result.GetStatus())

	respstr := string(result.GetPayload())
	fmt.Println("GetAllAllcoations:\n", respstr)

}

func TestGetAllocationModel(t *testing.T) {
	fmt.Println("Entering TestGetAllocationModel")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	role := "Client"
	org := "ORG1"

	result := stub.MockInvoke("t123", [][]byte{[]byte("getLatestAllocation"), []byte(role), []byte(org)})

	fmt.Println("Response status:", result.GetStatus())

	respstr := string(result.GetPayload())
	fmt.Println("GetAllocationModel:\n", respstr)

}

func TestPendingAllocationModel(t *testing.T) {
	fmt.Println("Entering TestPendingAllocationModel")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	role := "Approver"

	result := stub.MockInvoke("t123", [][]byte{[]byte("getPendingAllocations"), []byte(role)})

	fmt.Println("Response status:", result.GetStatus())

	respstr := string(result.GetPayload())
	fmt.Println("PendingAllocationModel:", respstr)

}

func TestReviewAllocationChangeDeclined(t *testing.T) {
	fmt.Println("Entering TestReviewAllocationChangeDeclined")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	role := "Approver"
	requestID := "R126"
	status := "Declined"
	orgName := "ORG4"

	result := stub.MockInvoke("t123", [][]byte{[]byte("reviewAllocationChange"), []byte(role), []byte(requestID), []byte(status)})

	fmt.Println("Response status:", result.GetStatus())

	resp, err := stub.GetState(requestID)

	if err != nil {
		fmt.Println(err)
	} else {
		if resp != nil {
			respstr := string(resp)
			fmt.Println("Fetched value:", respstr)
		} else {
			fmt.Println("No pending allocation found for RequestId#", requestID)
		}
	}

	resp1, err := stub.GetState(orgName)

	if err != nil {
		fmt.Println(err)
	} else {
		if resp1 != nil {
			respstr := string(resp1)
			fmt.Println("Fetched value:", respstr)
		} else {
			fmt.Println("No matching allocation found for Client Organisation", orgName)
		}
	}
}

func TestReviewAllocationChangeApproved(t *testing.T) {
	fmt.Println("Entering TestReviewAllocationChangeApproved")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	role := "Approver"
	requestID := "R126"
	status := "Approved"
	orgName := "ORG4"

	result := stub.MockInvoke("t123", [][]byte{[]byte("reviewAllocationChange"), []byte(role), []byte(requestID), []byte(status)})

	fmt.Println("Response status:", result.GetStatus())

	resp, err := stub.GetState(requestID)

	if err != nil {
		fmt.Println(err)
	} else {
		if resp != nil {
			respstr := string(resp)
			fmt.Println("Fetched value:", respstr)
		} else {
			fmt.Println("No pending allocation found for RequestId#", requestID)
		}
	}

	resp1, err := stub.GetState(orgName)

	if err != nil {
		fmt.Println(err)
	} else {
		if resp1 != nil {
			respstr := string(resp1)
			fmt.Println("Fetched value:", respstr)
		} else {
			fmt.Println("No matching allocation found for Client Organisation", orgName)
		}
	}
}

func TestModifyAllocation(t *testing.T) {
	fmt.Println("Entering TestModifyAllocation")

	// Instantiate mockStub using SmartAllocationChaincode as the target chaincode to unit test
	stub := shim.NewMockStub("mockStub", new(SmartAllocationChaincode))
	if stub == nil {
		t.Fatalf("MockStub creation failed")
	}

	stub.MockInit("t123", nil)

	var modifiedModel = allocationModel{
		RequestID:             "R124",
		Status:                "Approved",
		AllocationID:          "TEST1",
		ClientOrg:             "ORG1",
		ClientID:              "ORG1C1",
		ParentPortfolio:       "TESTP01",
		ChildPortfolio:        "TESTM01",
		Tolerances:            "1",
		TargetWeight:          "50",
		TargetWeightTolerance: "1",
		ShareClassConfigFlag:  true,
		InvstFrozenConfig:     "No Sell",
		TargetCash:            "0.01%",
		OverdraftLimit:        "10",
		MinimumTradeSize:      1}
	allocationModelAsBytes, _ := json.Marshal(modifiedModel)

	fmt.Println("Modify Allocation Input")
	fmt.Println(string(allocationModelAsBytes))

	role := "Client"

	result := stub.MockInvoke("t123", [][]byte{[]byte("modifyAllocation"),
		[]byte(role), []byte(string(allocationModelAsBytes))})

	fmt.Println("Response status:", result.GetStatus())

	resp, err := stub.GetState(modifiedModel.RequestID)

	if err != nil {
		fmt.Println(err)
	} else {
		if resp != nil {
			respstr := string(resp)
			fmt.Println("ModifiedAllocation:\n", respstr)
		} else {
			fmt.Println("No allocation found for RequestId#", modifiedModel.RequestID)
		}
	}

}
