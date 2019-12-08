package com.das.test.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.das.test.domain.Change;
import com.das.test.service.CoinService;

@RestController

public class CoinController {

	@Autowired
	CoinService coinService;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping(value="/{amount}",consumes = "application/json", produces = "application/json")
	public Change getCoinMapping (@PathVariable BigDecimal amount) throws Exception {
		return coinService.getCoinMapping(amount);
	}
}
