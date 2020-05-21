package com.codefountain.security.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListTransactionsDto {

	private final String username;
	private final List<TransactionDetailsDto> transactions;
}
