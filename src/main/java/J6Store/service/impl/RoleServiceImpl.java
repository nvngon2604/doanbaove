package J6Store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import J6Store.dao.RoleDAO;
import J6Store.entity.Role;
import J6Store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

}
