package com.codefountain.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.codefountain.security.model.CreateSupportQueryDto;
import com.codefountain.security.model.PostDto;
import com.codefountain.security.model.SupportQueryDto;
import com.codefountain.security.service.SupportQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SupportQueryController {

	private final SupportQueryService supportQueryService;
	
	@GetMapping("/support")
	public ModelAndView getQueries() {
		return new ModelAndView("support", "queries", supportQueryService.getSupportQueryForUser());
	}
	
	@GetMapping("/support/query/{id}")
	public ModelAndView getQuery(@PathVariable String id) {
		SupportQueryDto supportQueryDto = supportQueryService.getSupportQueryById(id);
		ModelAndView modelAndView = new ModelAndView("query", "query", supportQueryDto);
		PostDto postDto = new PostDto();
		postDto.setResolve(supportQueryDto.isResolved());
		modelAndView.addObject("newPost", new PostDto());
		return modelAndView;
	}
	
	@GetMapping("/support/compose")
	public ModelAndView createNewSupportQuery() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("newQuery", new CreateSupportQueryDto());
		modelAndView.setViewName("compose");
		return modelAndView;
		
	}
}
