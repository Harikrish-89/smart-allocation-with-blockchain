package com.cognizant.osp.smartallocation.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.osp.smartallocation.authentication.response.entities.IdcsUser;
import com.cognizant.osp.smartallocation.authentication.service.AuthenticationService;
import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;
import com.cognizant.osp.smartallocation.oabcs.service.ModifyAllocationService;
import com.cognizant.osp.smartallocation.oabcs.service.ReviewedAllocationService;
import com.cognizant.osp.smartallocation.oabcs.service.ViewAllocationService;
import com.cognizant.osp.smartallocation.oabcs.service.ViewPendingAllocationService;

@RestController
@RequestMapping(value = "/smartallocation", produces = { MediaType.APPLICATION_JSON_VALUE })
public class SmartAllocationRestService {

	@Autowired
	private ViewAllocationService viewAllocationService;

	@Autowired
	private ModifyAllocationService modifyAllocationService;

	@Autowired
	private ViewPendingAllocationService viewPendingAllocationService;

	@Autowired
	private ReviewedAllocationService reviewedAllocationService;

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/view", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public List<AllocationChangeRequest> viewAllocation(
			@RequestBody SmartAllocationServiceRestEntities smartAllocationRequestEntity) {
		return viewAllocationService
				.getAllocationChanges(smartAllocationRequestEntity.getCreds(), smartAllocationRequestEntity.getRole(),
						smartAllocationRequestEntity.getClientId())
				.stream().filter(allocationChangeRequest -> allocationChangeRequest.getPreviousAllocation() != null)
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/getLatestAllocations", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public List<AllocationChangeRequest> getLatestAllocations(
			@RequestBody SmartAllocationServiceRestEntities smartAllocationRequestEntity) {
		return viewAllocationService
				.getLatestAllocations(smartAllocationRequestEntity.getCreds(), smartAllocationRequestEntity.getRole(),
						smartAllocationRequestEntity.getOrg())
				.stream().filter(allocationChangeRequest -> allocationChangeRequest.getPreviousAllocation() != null)
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/modify", method = { POST }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public boolean modifyAllocation(@RequestBody AllocationRequestEntitiy allocationRequestEntitiy) {
		return modifyAllocationService.modifyAllocation(allocationRequestEntitiy.getAllocationChangeRequest(),
				allocationRequestEntitiy.getSmartAllocationRequestEntity().getCreds(),
				allocationRequestEntitiy.getSmartAllocationRequestEntity().getRole());
	}

	@RequestMapping(value = "/pending", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public List<AllocationChangeRequest> getPendingAllocations(
			@RequestBody SmartAllocationServiceRestEntities smartAllocationRequestEntity) {
		return viewPendingAllocationService
				.getPendingAllocations(smartAllocationRequestEntity.getCreds(), smartAllocationRequestEntity.getRole())
				.stream().filter(allocationChangeRequest -> allocationChangeRequest.getPreviousAllocation() != null)
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/reviewed", method = { RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@CrossOrigin
	public boolean performOperation(@RequestBody AllocationRequestEntitiy allocationRequestEntitiy) throws Exception {
		return reviewedAllocationService.approve(allocationRequestEntitiy.getAllocationChangeRequest().getRequestId(),
				allocationRequestEntitiy.getAllocationChangeRequest().getStatus(),
				allocationRequestEntitiy.getSmartAllocationRequestEntity().getCreds(),
				allocationRequestEntitiy.getSmartAllocationRequestEntity().getRole());
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE })
	@CrossOrigin
	public IdcsUser authenticateUser(@RequestBody String userNamePassword) {
		return authenticationService.getAuthenticatedUser(userNamePassword);
	}
}
