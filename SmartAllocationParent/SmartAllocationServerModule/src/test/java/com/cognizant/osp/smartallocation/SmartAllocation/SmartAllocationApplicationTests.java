package com.cognizant.osp.smartallocation.SmartAllocation;

import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.osp.smartallocation.authentication.service.AuthenticationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartAllocationApplicationTests {

	@Autowired
	private AuthenticationService authService;

	@Test
	public void contextLoads() {
		System.out.println(authService.getAuthenticatedUser(
				Base64.getEncoder().encodeToString("harikrishnan.sridhar@cognizant.com:H4rikrishnan!".getBytes())));
	}

}
