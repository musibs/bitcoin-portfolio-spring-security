package com.codefountain.security.service;

import org.springframework.stereotype.Service;

import com.codefountain.security.entity.Post;
import com.codefountain.security.entity.SupportQuery;
import com.codefountain.security.model.CreateSupportQueryDto;
import com.codefountain.security.model.PostDto;
import com.codefountain.security.repository.SupportQueryRepository;
import com.codefountain.security.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportCommadServiceNoSql implements SupportCommadService {

	private final SupportQueryRepository supportQueryRepository;
	
	@Override
	public void createQuery(CreateSupportQueryDto query) {
		SupportQuery supportQuery = new SupportQuery(CommonUtils.getUserName(), query.getSubject());
		supportQuery.addPost(query.getContent(), CommonUtils.getUserName());
		supportQueryRepository.save(supportQuery);
	}

	@Override
	public void postToQuery(PostDto postDto) {
		Post post = new Post(CommonUtils.getUserName(), postDto.getContent(), System.currentTimeMillis());
		SupportQuery supportQuery = supportQueryRepository.findById(postDto.getQueryId()).get();
		supportQuery.addPost(post);
		if(postDto.isResolve()) {
			supportQuery.resolve();
		}
		supportQueryRepository.save(supportQuery);
	}

	@Override
	public void resolveQuery(String id) {
		SupportQuery supportQuery = supportQueryRepository.findById(id).get();
		supportQuery.resolve();
		supportQueryRepository.save(supportQuery);
	}

}
