package com.github.guilherme.crudprova.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.guilherme.crudprova.model.LimitesCliente;
import com.github.guilherme.crudprova.service.LimitesClienteService;
import com.github.guilherme.crudprova.util.CustomErrorType;

/**
 * API Rest para comunicacao com o cliente AngularJS
 * 
 * @author Guilherme
 * @see {@link LimitesClienteService}
 */
@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	LimitesClienteService limitesClienteService;

	// -------------------Retrieve All
	// Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<LimitesCliente>> listAllUsers() {
		List<LimitesCliente> users = limitesClienteService.findAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<LimitesCliente>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		LimitesCliente user = limitesClienteService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new CustomErrorType("User with id " + id + " not found"));

		}
		return new ResponseEntity<LimitesCliente>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody LimitesCliente limiteCliente, UriComponentsBuilder ucBuilder) {
		logger.info("Criando LimiteCliente : {}", limiteCliente);

		if (limitesClienteService.isUserExist(limiteCliente)) {
			logger.error("Unable to create. A User with name {} already exist", limiteCliente.getName());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomErrorType(
					"Unable to create. A User with name " + limiteCliente.getName() + " already exist."));
		}
		limitesClienteService.saveUser(limiteCliente);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(limiteCliente.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User
	// ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody LimitesCliente limiteCliente) {
		logger.info("Updating User with id {}", id);

		LimitesCliente limiteClienteAtual = limitesClienteService.findById(id);

		if (limiteClienteAtual == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new CustomErrorType("Unable to upate. User with id " + id + " not found."));
		}

		limiteClienteAtual.setName(limiteCliente.getName());
		limiteClienteAtual.setLimite(limiteCliente.getLimite());
		limiteClienteAtual.setRisco(limiteCliente.getRisco());

		limitesClienteService.updateUser(limiteClienteAtual);
		return new ResponseEntity<LimitesCliente>(limiteClienteAtual, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		LimitesCliente limiteCliente = limitesClienteService.findById(id);
		if (limiteCliente == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new CustomErrorType("Unable to delete. User with id " + id + " not found."));
		}
		limitesClienteService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<LimitesCliente> deleteAllUsers() {
		logger.info("Deleting All Users");

		limitesClienteService.deleteAllUsers();
		return new ResponseEntity<LimitesCliente>(HttpStatus.NO_CONTENT);
	}

}