package com.github.guilherme.crudprova.service;

import java.util.List;

import com.github.guilherme.crudprova.model.LimitesCliente;

/**
 * Servico principal
 * @author Guilherme
 * @see {@link LimitesCliente}
 */
public interface LimitesClienteService {

	LimitesCliente findById(Long id);

	LimitesCliente findByName(String name);

	void saveUser(LimitesCliente user);

	void updateUser(LimitesCliente user);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<LimitesCliente> findAllUsers();

	boolean isUserExist(LimitesCliente user);
}