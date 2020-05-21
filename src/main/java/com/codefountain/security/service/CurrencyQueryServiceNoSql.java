package com.codefountain.security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.codefountain.security.model.CryptoCurrencyDto;
import com.codefountain.security.repository.CryptoCurrencyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyQueryServiceNoSql implements CurrencyQueryService {

	private final CryptoCurrencyRepository cryptoCurrencyRepository;
	private Map<String, CryptoCurrencyDto> cryptoCurrencies;

	@Override
	public List<CryptoCurrencyDto> getSupportedCurrencies() {
		if (Objects.isNull(cryptoCurrencies)) {
			this.cryptoCurrencies = new HashMap<>();
			cryptoCurrencyRepository.findAll()
					.forEach(currency -> cryptoCurrencies.put(currency.getSymbol(), CryptoCurrencyDto
							.builder().name(currency.getName()).symbol(currency.getSymbol()).build()));
		}
		return new ArrayList<>(cryptoCurrencies.values());
	}

	@Override
	public CryptoCurrencyDto getCryptoCurrency(String symbol) {
		return cryptoCurrencies.get(symbol);
	}

}
