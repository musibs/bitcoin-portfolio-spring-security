package com.codefountain.security.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class CryptoCurrencyDto {
	
	private final String symbol;
	private final String name;
}

