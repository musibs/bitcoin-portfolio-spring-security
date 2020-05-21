package com.codefountain.security.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class PositionDto {

	private final CryptoCurrencyDto cryptoCurrency;
	private final BigDecimal quantity;
	private final BigDecimal value;
}
