package J6Store.service;

import java.util.List;

import J6Store.entity.Authority;

public interface AuthorityService {
	
	public List<Authority> findAll();
	
	public Authority create(Authority auth);
	
	public void delete(Integer id);
	
	public List<Authority> findAuthoritiesOfAdministrators();
	
	public List<Authority> findAuthoritiesOfCust();
	
	long authorityDirector();
	
	long authorityStaff();
}
