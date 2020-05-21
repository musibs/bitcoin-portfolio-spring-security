package com.codefountain.security.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class DefaultPricingServiceNosql implements PricingService {

	@Override
	public BigDecimal getCurrencyPriceForCrypto(String symbol) {
		return new BigDecimal("1000");
	}

	
}
