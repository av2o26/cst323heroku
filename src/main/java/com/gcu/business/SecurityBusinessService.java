package com.gcu.business;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityBusinessService
{
	public boolean authenticate(String username, String password)
	{
		log.info("Hello from the SecurityBusinessService.");
		return true;
	}
}
