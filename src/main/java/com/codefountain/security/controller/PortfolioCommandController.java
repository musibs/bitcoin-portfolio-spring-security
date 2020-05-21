package com.codefountain.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codefountain.security.model.AddTransactionToPortfolioDto;
import com.codefountain.security.model.DeleteTransactionDto;
import com.codefountain.security.service.PortfolioCommandService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PortfolioCommandController {

	private final PortfolioCommandService portfolioCommandService;
	
	@PostMapping("/portfolio/transactions")
	public ModelAndView addTransactionToPortfolio(@ModelAttribute("transaction") AddTransactionToPortfolioDto request) {
		portfolioCommandService.addTransactionToPortfolio(request);
		return new ModelAndView("redirect:/portfolio");
	}
	
	@DeleteMapping("/portfolio/transactions")
	public ModelAndView deleteTransactionToPortfolio(@ModelAttribute("selected") DeleteTransactionDto request) {
		for(String id : request.getId()) {
			portfolioCommandService.removeTransactionFromPortfolio(id);
		}
		return new ModelAndView("redirect:/portfolio");
	}
}
