package J6Store.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import J6Store.dao.AccountDAO;
import J6Store.entity.Account;
import J6Store.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/statistical/users")
public class StatisticalRestUser {

	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountDAO dao;
	
	@GetMapping
	public long statisticalUser(){
		return accountService.count();
	}
}
