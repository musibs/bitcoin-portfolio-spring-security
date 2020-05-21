package com.codefountain.security.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.codefountain.security.model.AddTransactionToPortfolioDto;
import com.codefountain.security.model.DeleteTransactionDto;
import com.codefountain.security.model.ListTransactionsDto;
import com.codefountain.security.service.PortfolioQueryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PortfolioQueryController {

	private final PortfolioQueryService portfolioQueryService;

	@GetMapping("/")
	public String index() {
		return "redirect:/portfolio";
	}

	@GetMapping("/portfolio")
	public ModelAndView positions() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("positionsResponse", portfolioQueryService.getPortfolioPositions());
		modelAndView.addObject("transaction", new AddTransactionToPortfolioDto());
		return modelAndView;
	}

	@GetMapping(value = { "/portfolio/transactions", "/portfolio/transactions/{symbol}" })
	public ModelAndView listTransactionsForPortfolio(@PathVariable Optional<String> symbol) {
		ModelAndView modelAndView = new ModelAndView();
		ListTransactionsDto listTransactions = portfolioQueryService.getPortfolioTransactions();
		if (symbol.isPresent()) {
			modelAndView.addObject("transactions", listTransactions.getTransactions().stream()
					.filter(transaction -> transaction.getSymbol().equals(symbol.get())).collect(Collectors.toList()));
		}
		else {
			modelAndView.addObject("transactions", listTransactions.getTransactions());
		}
		
		modelAndView.addObject("selected", new DeleteTransactionDto());
		modelAndView.setViewName("transactions");
		return modelAndView;
	}

}
