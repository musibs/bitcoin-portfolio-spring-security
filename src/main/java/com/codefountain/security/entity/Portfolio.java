package com.codefountain.security.entity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Document
@RequiredArgsConstructor
public class Portfolio {

	@Id
	private String id;
	@NonNull
	private final String userName;
	private final List<Transaction> transactions;

	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(this.transactions);
	}

	public void deleteTransaction(Transaction transaction) {
		this.transactions.remove(transaction);
	}

	public List<Transaction> getTransactionByCurrencySymbol(String symbol) {
		return this.transactions.stream()
				.filter(transaction -> transaction.getCryptoCurrency().getSymbol().equals(symbol))
				.collect(Collectors.toList());
	}

	public Transaction getTransactionById(String id) {
		return this.transactions.stream()
				.filter(transaction -> transaction.getId().equals(id))
				.collect(Collectors.toList()).get(0);
	}
}
