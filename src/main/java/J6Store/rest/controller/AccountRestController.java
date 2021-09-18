package J6Store.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import J6Store.entity.Account;
import J6Store.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
		if(admin.orElse(false)) {
			return accountService.getAdminstrators();
		}
		return accountService.findAll();
	}
	
	@GetMapping("/user")
	public List<Account> getUsersAccounts(@RequestParam("cust") Optional<Boolean> cust){
		if(cust.orElse(false)) {
			return accountService.getUsers();
		}
		return accountService.findAll();
	}
	
	@DeleteMapping("{username}")
	public void update(@PathVariable("username") String username) {
		accountService.delete(username);
	}
	
	
}
