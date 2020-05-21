package com.codefountain.security.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Transaction {

	@Id
	private String id;
	private final CryptoCurrency cryptoCurrency;
	private final Type type;
	private final BigDecimal quantity;
	private final BigDecimal price;
	private final long timestamp;
}
