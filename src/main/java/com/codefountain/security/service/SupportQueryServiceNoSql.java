package com.codefountain.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codefountain.security.entity.SupportQuery;
import com.codefountain.security.model.PostDto;
import com.codefountain.security.model.SupportQueryDto;
import com.codefountain.security.repository.SupportQueryRepository;
import com.codefountain.security.utils.CommonUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupportQueryServiceNoSql implements SupportQueryService {

	private final SupportQueryRepository supportQueryRepository;

	@Override
	public List<SupportQueryDto> getSupportQueryForUser() {
		List<SupportQuery> supportQueries = supportQueryRepository.findByUsername(CommonUtils.getUserName());
		return supportQueries.stream().map(supportQuery -> getSupportQueryDto(supportQuery))
				.collect(Collectors.toList());
	}

	private SupportQueryDto getSupportQueryDto(SupportQuery supportQuery) {
		List<PostDto> posts = supportQuery.getPosts().stream().map(post -> {
			return new PostDto(post.getId(), post.getContent(), post.getUserName(), supportQuery.isResolved());
		}).collect(Collectors.toList());

		return new SupportQueryDto(supportQuery.getId(), supportQuery.getSubject(), supportQuery.getCreated(),
				supportQuery.getUsername(), supportQuery.isResolved(), posts);
	}

	@Override
	public SupportQueryDto getSupportQueryById(String id) {
		return getSupportQueryDto(supportQueryRepository.findById(id).get());
	}

	@Override
	public List<SupportQueryDto> getSupportQueryForAllUser() {
		return supportQueryRepository.findAll().stream().map(supportQuery -> getSupportQueryDto(supportQuery))
				.collect(Collectors.toList());
	}

}
