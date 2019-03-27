package com.github.guilherme.crudprova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.guilherme.crudprova.model.LimitesCliente;
import com.github.guilherme.crudprova.repositories.LimitesClienteRepository;

/**
 * Primeira implementacao do Servico principal
 * @author Guilherme
 * @see {@link LimitesClienteRepository}, {@link LimitesCliente}
 */
@Service("limitesClienteService")
@Transactional
public class LimitesClienteServiceImpl implements LimitesClienteService {

	@Autowired
	private LimitesClienteRepository limitesClienteRepository;

	public LimitesCliente findById(Long id) {
		return limitesClienteRepository.findOne(id);
	}

	public LimitesCliente findByName(String name) {
		return limitesClienteRepository.findByName(name);
	}

	public void saveUser(LimitesCliente user) {
		limitesClienteRepository.save(user);
	}

	public void updateUser(LimitesCliente user) {
		saveUser(user);
	}

	public void deleteUserById(Long id) {
		limitesClienteRepository.delete(id);
	}

	public void deleteAllUsers() {
		limitesClienteRepository.deleteAll();
	}

	public List<LimitesCliente> findAllUsers() {
		return limitesClienteRepository.findAll();
	}

	public boolean isUserExist(LimitesCliente user) {
		return findByName(user.getName()) != null;
	}

}
