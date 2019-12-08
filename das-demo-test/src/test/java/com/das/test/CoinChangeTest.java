package com.das.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc //need this in Spring Boot test
@SpringBootTest(classes= {Application.class}, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoinChangeTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHappyPathCoins() throws Exception{
		mockMvc.perform(post("/{amount}", 1.01).contentType("application/json")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.dollars").value("1")).andExpect(jsonPath("$.halfDollars").value("0")).andExpect(jsonPath("$.quarters").value("0")).andExpect(jsonPath("$.dimes").value("0")).andExpect(jsonPath("$.nickels").value("0")).andExpect(jsonPath("$.pennies").value("1"));
	}
	
	@Test
	public void test99cents() throws Exception{
		mockMvc.perform(post("/{amount}", 0.99).contentType("application/json")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.dollars").value("0")).andExpect(jsonPath("$.halfDollars").value("1")).andExpect(jsonPath("$.quarters").value("1")).andExpect(jsonPath("$.dimes").value("2")).andExpect(jsonPath("$.nickels").value("0")).andExpect(jsonPath("$.pennies").value("4"));
		 
	}
	
	@Test
	public void testDollarAnd56Cents() throws Exception{
		mockMvc.perform(post("/{amount}", 1.56).contentType("application/json")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.dollars").value("1")).andExpect(jsonPath("$.halfDollars").value("1")).andExpect(jsonPath("$.quarters").value("0")).andExpect(jsonPath("$.dimes").value("0")).andExpect(jsonPath("$.nickels").value("1")).andExpect(jsonPath("$.pennies").value("1"));
		 
	}
	
	@Test
	public void test12DollarsAnd85Cents() throws Exception{
		mockMvc.perform(post("/{amount}", 12.85).contentType("application/json")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.dollars").value("12")).andExpect(jsonPath("$.halfDollars").value("1")).andExpect(jsonPath("$.quarters").value("1")).andExpect(jsonPath("$.dimes").value("1")).andExpect(jsonPath("$.nickels").value("0")).andExpect(jsonPath("$.pennies").value("0")); 
	}

}
