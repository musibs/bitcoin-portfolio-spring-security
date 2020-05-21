package com.codefountain.security.service;

import com.codefountain.security.model.AddTransactionToPortfolioDto;

public interface PortfolioCommandService {

	void addTransactionToPortfolio(AddTransactionToPortfolioDto addTransactionToPortfolioDto);
	void removeTransactionFromPortfolio(String transactionId);
}
