package com.codefountain.security.service;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.codefountain.security.entity.CryptoCurrency;
import com.codefountain.security.entity.Portfolio;
import com.codefountain.security.entity.Transaction;
import com.codefountain.security.entity.Type;
import com.codefountain.security.model.AddTransactionToPortfolioDto;
import com.codefountain.security.repository.CryptoCurrencyRepository;
import com.codefountain.security.repository.PortfolioRepository;
import com.codefountain.security.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioCommandServiceNoSql implements PortfolioCommandService {
	
	private final PortfolioRepository portfolioRepository;
	private final CryptoCurrencyRepository cryptoCurrencyRepository;
	
	@Override
	public void addTransactionToPortfolio(AddTransactionToPortfolioDto addTransactionToPortfolioDto) {
		Portfolio portfolio = portfolioRepository.findByUserName(CommonUtils.getUserName());
		portfolio.addTransaction(createTransactionEntity(addTransactionToPortfolioDto));
		portfolioRepository.save(portfolio);
	}

	@Override
	public void removeTransactionFromPortfolio(String transactionId) {
		Portfolio portfolio = portfolioRepository.findByUserName(getUserName());
		Transaction transaction = portfolio.getTransactionById(transactionId);
		portfolio.deleteTransaction(transaction);
		portfolioRepository.save(portfolio);
	}
	
	private String getUserName() {
		return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
	private Transaction createTransactionEntity(AddTransactionToPortfolioDto request) {
		CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBySymbol(request.getCryptoSymbol());
		return Transaction.builder()
				.id(new ObjectId().toHexString())
				.cryptoCurrency(cryptoCurrency)
				.type(Type.valueOf(request.getType()))
				.quantity(new BigDecimal(request.getQuantity()))
				.price(new BigDecimal(request.getPrice()))
				.timestamp(System.currentTimeMillis())
				.build();
				
	}

}
