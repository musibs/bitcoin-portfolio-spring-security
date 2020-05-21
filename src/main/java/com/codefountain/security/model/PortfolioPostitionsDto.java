package com.codefountain.security.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PortfolioPostitionsDto {

	private final String firstName;
	private final String lastName;
	private final List<PositionDto> positions;
	private final Map<String, String> cryptoCurrencies;
}
