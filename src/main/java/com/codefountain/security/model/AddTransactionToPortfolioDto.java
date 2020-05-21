package com.codefountain.security.model;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AddTransactionToPortfolioDto {
	
	@NonNull
	private String cryptoSymbol;
	@NonNull
	private String quantity;
	@NonNull
	private String price;
	@NonNull
	private String type;
	private String userName;

}
