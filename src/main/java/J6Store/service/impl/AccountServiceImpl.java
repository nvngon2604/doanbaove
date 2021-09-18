package J6Store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import J6Store.dao.AccountDAO;
import J6Store.entity.Account;
import J6Store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDAO adao;

	@Override
	public Account findById(String username) {
		return adao.findById(username).get();
	}

	@Override
	public <S extends Account> S save(S entity) {
		return adao.save(entity);
	}

	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return adao.findOne(example);
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return adao.findAll(pageable);
	}

	@Override
	public List<Account> findAll() {
		return adao.findAll();
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return adao.findAll(sort);
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return adao.findAllById(ids);
	}

	

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return adao.saveAll(entities);
	}

	@Override
	public void flush() {
		adao.flush();
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return adao.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return adao.existsById(id);
	}

	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return adao.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return adao.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		adao.deleteInBatch(entities);
	}

	@Override
	public <S extends Account> long count(Example<S> example) {
		return adao.count(example);
	}

	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		return adao.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		adao.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return adao.count();
	}

	@Override
	public void deleteById(String id) {
		adao.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		adao.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(String username) {
		adao.deleteById(username);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		adao.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		adao.deleteAllInBatch();
	}

	@Override
	public Account getOne(String id) {
		return adao.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		adao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		adao.deleteAll();
	}

	@Override
	public Account getById(String id) {
		return adao.getById(id);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		return adao.findAll(example);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		return adao.findAll(example, sort);
	}

	@Override
	public List<Account> getAdminstrators() {
		return adao.getAdminstrators();
	}

	@Override
	public Account statisticalAccount() {
		return adao.statisticalAccount();
	}

	@Override
	public List<Account> getUsers() {
		return adao.getUsers();
	}
	
	
}
