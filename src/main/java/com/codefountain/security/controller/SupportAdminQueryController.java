package com.codefountain.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codefountain.security.service.SupportQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SupportAdminQueryController {

	private final SupportQueryService supportQueryService;
	
	@GetMapping("/support/admin")
	public ModelAndView getSupportQueries() {
		return new ModelAndView("support", "queries", supportQueryService.getSupportQueryForUser());
	}
}
