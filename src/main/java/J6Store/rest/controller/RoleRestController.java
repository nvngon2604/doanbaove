package J6Store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import J6Store.entity.Role;
import J6Store.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public List<Role> getAll(){
		return roleService.findAll();
	}
}
