package com.codefountain.security.service;

import java.math.BigDecimal;

public interface PricingService {
	
	BigDecimal getCurrencyPriceForCrypto(String symbol);

}
