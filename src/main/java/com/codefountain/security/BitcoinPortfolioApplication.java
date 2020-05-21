package com.codefountain.security;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codefountain.security.entity.CryptoCurrency;
import com.codefountain.security.entity.Portfolio;
import com.codefountain.security.repository.CryptoCurrencyRepository;
import com.codefountain.security.repository.PortfolioRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class BitcoinPortfolioApplication {
	
	private final PortfolioRepository portfolioRepository;
	private final CryptoCurrencyRepository cryptoCurrencyRepository;

	public static void main(String[] args) {
		SpringApplication.run(BitcoinPortfolioApplication.class, args);
	}
	
	@Bean
	public void init() {
		CryptoCurrency bitCoin = new CryptoCurrency("BTC", "BitCoin");
		CryptoCurrency litCoin = new CryptoCurrency("LTC", "LitCoin");
		
		cryptoCurrencyRepository.save(bitCoin);
		cryptoCurrencyRepository.save(litCoin);
		portfolioRepository.save(new Portfolio("user", new ArrayList<>()));
	}
}
