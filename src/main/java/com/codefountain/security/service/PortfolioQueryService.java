package com.codefountain.security.service;

import com.codefountain.security.model.ListTransactionsDto;
import com.codefountain.security.model.PortfolioPostitionsDto;

public interface PortfolioQueryService {
	
	PortfolioPostitionsDto getPortfolioPositions();
	ListTransactionsDto getPortfolioTransactions();

}
