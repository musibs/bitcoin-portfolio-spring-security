package com.codefountain.security.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codefountain.security.entity.Portfolio;
import com.codefountain.security.entity.Transaction;
import com.codefountain.security.model.CryptoCurrencyDto;
import com.codefountain.security.model.ListTransactionsDto;
import com.codefountain.security.model.PortfolioPostitionsDto;
import com.codefountain.security.model.PositionDto;
import com.codefountain.security.model.TransactionDetailsDto;
import com.codefountain.security.repository.PortfolioRepository;
import com.codefountain.security.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PortfolioQueryServiceNoSql implements PortfolioQueryService {

	private final CurrencyQueryService currencyQueryService;
	private final PortfolioRepository portfolioRepository;
	private final PricingService pricingService;

	@Override
	public PortfolioPostitionsDto getPortfolioPositions() {
		List<CryptoCurrencyDto> cryptoCurrencies = currencyQueryService.getSupportedCurrencies();
		Portfolio portfolio = portfolioRepository.findByUserName(CommonUtils.getUserName());
		List<PositionDto> positions = calculatePositions(cryptoCurrencies, portfolio);
		Map<String, String> cryptoMap = convertCryptoListToMap(cryptoCurrencies);

		return new PortfolioPostitionsDto("", "", positions, cryptoMap);
	}

	private List<PositionDto> calculatePositions(List<CryptoCurrencyDto> cryptos, Portfolio portfolio) {
		List<PositionDto> positions = new ArrayList<PositionDto>();
		for (CryptoCurrencyDto cryptoCurrencyDto : cryptos) {
			List<Transaction> transactions = portfolio.getTransactionByCurrencySymbol(cryptoCurrencyDto.getSymbol());
			BigDecimal quantity = calculatePositionQuantity(transactions);
			BigDecimal currentPrice = pricingService.getCurrencyPriceForCrypto(cryptoCurrencyDto.getSymbol());
			BigDecimal positionValue = calculatePositionValue(quantity, currentPrice);
			positions.add(PositionDto.builder().quantity(quantity).value(positionValue)
					.cryptoCurrency(cryptoCurrencyDto).build());
		}
		return positions;
	}

	private Map<String, String> convertCryptoListToMap(List<CryptoCurrencyDto> crptos) {
		Map<String, String> cryptoMap = new HashMap<>();
		for (CryptoCurrencyDto cryptoCurrencyDto : crptos) {
			cryptoMap.put(cryptoCurrencyDto.getSymbol(), cryptoCurrencyDto.getName());
		}
		return cryptoMap;
	}

	private BigDecimal calculatePositionValue(BigDecimal quantity, BigDecimal currentPrice) {
		return quantity.multiply(currentPrice);
	}

	private BigDecimal calculatePositionQuantity(List<Transaction> cryptoTransactions) {
		BigDecimal quantity = BigDecimal.ZERO;
		for (Transaction transaction : cryptoTransactions) {
			switch (transaction.getType()) {
			case BUY:
				quantity = quantity.add(transaction.getQuantity());
				break;
			case SELL:
				quantity= quantity.subtract(transaction.getQuantity());
				break;
			default:
				break;
			}
		}

		return quantity;
	}

	@Override
	public ListTransactionsDto getPortfolioTransactions() {
		Portfolio portfolio = portfolioRepository.findByUserName(CommonUtils.getUserName());
		List<Transaction> transactions = portfolio.getTransactions();
		return createListTransactionsResponse(transactions);
	}

	private ListTransactionsDto createListTransactionsResponse(List<Transaction> transactions) {
		List<TransactionDetailsDto> transactionDetails = new ArrayList<>();
		for(Transaction transaction : transactions) {
			transactionDetails.add(new TransactionDetailsDto(transaction.getId(), 
					transaction.getCryptoCurrency().getSymbol(), transaction.getType().name(), 
					transaction.getQuantity().toString(), transaction.getPrice().toString()));
		}
		return new ListTransactionsDto(CommonUtils.getUserName(), transactionDetails);
	}

}
