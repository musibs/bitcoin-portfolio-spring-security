package com.codefountain.security.service;

import java.util.List;

import com.codefountain.security.model.SupportQueryDto;

public interface SupportQueryService {

	List<SupportQueryDto> getSupportQueryForUser();
	SupportQueryDto getSupportQueryById(String id);
	List<SupportQueryDto> getSupportQueryForAllUser();
}
