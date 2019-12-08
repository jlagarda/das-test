package com.das.test.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.das.test.domain.Change;

@Service
public class CoinService {
	
	public Change getCoinMapping(BigDecimal amount) throws Exception {
		Change change = defaultValue();
		if (amount.compareTo(BigDecimal.ZERO)==-1) {
			throw new Exception("The amount must be greater than zero");
		}
		
		determineDollars(amount, change);
		BigDecimal newValue = decrementBalance(amount, change.getDollars(), BigDecimal.ONE);
		determineHalfDollars(newValue, change);
		newValue = decrementBalance(newValue, change.getHalfDollars(), new BigDecimal("0.50"));
		determineQuarters(newValue, change);
		newValue = decrementBalance(newValue, change.getQuarters(), new BigDecimal("0.25"));
		determineDimes(newValue, change);
		newValue = decrementBalance(newValue, change.getDimes(), new BigDecimal("0.10"));
		determineNickels(newValue, change);
		newValue = decrementBalance(newValue, change.getNickels(), new BigDecimal("0.05"));
		determinePennies(newValue, change);
		return change;
	}
	
	public Change defaultValue() {
		Change returnObject = new Change();
		returnObject.setDollars("0");
		returnObject.setHalfDollars("0");
		returnObject.setQuarters("0");
		returnObject.setDimes("0");
		returnObject.setNickels("0");
		returnObject.setPennies("0");
		return returnObject;
	}
	
	public BigDecimal decrementBalance(BigDecimal currentValue, String coin, BigDecimal coinValue) {
		BigDecimal amountToDecrement = BigDecimal.valueOf(Double.valueOf(coin)).multiply(coinValue).setScale(2);
		BigDecimal newValue = currentValue.subtract(amountToDecrement).setScale(2);
		return newValue;
	}
	
	public void determineDollars(BigDecimal value, Change change) {
		
		if(value.compareTo(BigDecimal.ZERO)==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal(1))>=0) {
			BigDecimal remainder = value.remainder(BigDecimal.ONE);
			change.setDollars(String.valueOf((value.subtract(remainder)).setScale(0)));	
		}
		else {
		change.setDollars("0");
		}
	}
	
	public void determineHalfDollars(BigDecimal value, Change change) {
		
		if(value.compareTo(BigDecimal.ZERO)==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal("0.50"))==1) {
		change.setHalfDollars("1");	
		}
		else {
		change.setHalfDollars("0");
		}
	}
	
	public void determineQuarters(BigDecimal value, Change change) {
		
		if(value.compareTo(BigDecimal.ZERO)==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal("0.25"))==1) {
		change.setQuarters("1");	
		}
		else {
		change.setQuarters("0");
		}
		
	}
	
	public void determineDimes(BigDecimal value, Change change) {

		
		
		if(value.compareTo(BigDecimal.ZERO)==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal("0.10"))>=0) {
			if(value.compareTo(new BigDecimal("0.10"))==1){
				change.setDimes("2");
			}
			else {
				change.setDimes("1");
			}
		}
		else {
		change.setDimes("0");
		}
	}
	
	public void determineNickels(BigDecimal value, Change change) {
		
		if(value.compareTo(BigDecimal.ZERO)==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal("0.05"))==1) {
		change.setNickels("1");	
		}
		else {
		change.setNickels("0");
		}
	}
	
	public void determinePennies(BigDecimal value, Change change) {

		
		if(value.compareTo(BigDecimal.ZERO) ==0) {
		return;
		}
		else if(value.compareTo(new BigDecimal(0))==1) {
			change.setPennies(String.valueOf(value.multiply(new BigDecimal("100")).setScale(0)));
		}
		else {
		change.setPennies("0");
		}
		
	}



}
