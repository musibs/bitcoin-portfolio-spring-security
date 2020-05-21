package com.codefountain.security.service;

import java.util.List;

import com.codefountain.security.model.CryptoCurrencyDto;

public interface CurrencyQueryService {

	List<CryptoCurrencyDto> getSupportedCurrencies();
	CryptoCurrencyDto getCryptoCurrency(String symbol);
}
