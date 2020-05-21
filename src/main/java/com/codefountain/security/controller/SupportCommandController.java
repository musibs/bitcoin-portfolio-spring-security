package com.codefountain.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codefountain.security.model.CreateSupportQueryDto;
import com.codefountain.security.model.PostDto;
import com.codefountain.security.service.SupportCommadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SupportCommandController {

	private final SupportCommadService supportCommadService;
	
	@PostMapping("/support")
	public String createNewSupport(@ModelAttribute CreateSupportQueryDto createSupportQueryDto) {
		supportCommadService.createQuery(createSupportQueryDto);
		return "redirect:/support";
	}
	
	@PostMapping("/support/query/{id}")
	public String postToQuery(@ModelAttribute PostDto postDto, @PathVariable String id) {
		postDto.setQueryId(id);
		supportCommadService.postToQuery(postDto);
		if(postDto.isResolve()) {
			this.supportCommadService.resolveQuery(id);
		}
		return "redirect:/support/query/"+postDto.getQueryId();
	}
}
