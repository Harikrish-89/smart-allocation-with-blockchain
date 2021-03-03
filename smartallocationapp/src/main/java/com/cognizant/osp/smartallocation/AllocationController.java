package com.cognizant.osp.smartallocation;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/allocation")
@RestController
public class AllocationController {

	private static HashMap<String, Allocation> allocationsMap = new HashMap<String, Allocation>();

	static {
		Allocation allocation = new Allocation();
		allocation.setAllocationId("TEST1");
		allocation.setParentPortfolio("TESTP01");
		allocation.setChildPortfolio("TESTM01");
		allocation.setClientOrg("ORG1");
		allocation.setInvstFrozenConfig("Open");
		allocation.setMinimumTradeSize(1);
		allocation.setShareClassConfigFlag(true);
		allocation.setTargetCash("0.01%");
		allocation.setOverdraftLimit("10");
		allocation.setTolerances("1");
		allocation.setTargetWeight("100");
		allocation.setTargetWeightTolerance("1");

		allocationsMap.put(allocation.getAllocationId(), allocation);

		allocation = new Allocation();
		allocation.setAllocationId("TEST2");
		allocation.setParentPortfolio("TESTP02");
		allocation.setChildPortfolio("TESTM02");
		allocation.setClientOrg("ORG2");
		allocation.setInvstFrozenConfig("Open");
		allocation.setMinimumTradeSize(10);
		allocation.setShareClassConfigFlag(false);
		allocation.setTargetCash("0.01%");
		allocation.setOverdraftLimit("20");
		allocation.setTolerances("2");
		allocation.setTargetWeight("100");
		allocation.setTargetWeightTolerance("1");
		allocationsMap.put(allocation.getAllocationId(), allocation);
	}

	public AllocationController() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.GET, path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public <T> ResponseEntity getAllAllocations() {
		ResponseEntity<T> response = null;

		if (allocationsMap != null && !allocationsMap.isEmpty()) {
			response = new ResponseEntity<T>((T) allocationsMap.values(), HttpStatus.OK);
			
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.POST }, path = "/modifyallocation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public <T> ResponseEntity modifyAllocation(@RequestBody Allocation allocation) {
		ResponseEntity<T> response = null;

		if (allocationsMap == null || allocationsMap.isEmpty()) {

			allocationsMap = new HashMap<String, Allocation>();

		}

		allocationsMap.put(allocation.getAllocationId(), allocation);

		response = new ResponseEntity<>(HttpStatus.OK);
		return response;

	}

}
