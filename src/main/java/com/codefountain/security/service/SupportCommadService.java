package com.codefountain.security.service;

import com.codefountain.security.model.CreateSupportQueryDto;
import com.codefountain.security.model.PostDto;

public interface SupportCommadService {

	void createQuery(CreateSupportQueryDto query);
	void postToQuery(PostDto postDto);
	void resolveQuery(String id);
}
