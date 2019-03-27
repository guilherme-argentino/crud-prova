package com.github.guilherme.crudprova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.guilherme.crudprova.model.RiscoCliente;

/**
 * Controller responsavel pela geracao da tela principal
 * @author Guilherme
 */
@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Prova (Guilherme)");
		modal.addAttribute("riscos", RiscoCliente.values());
		return "index";
	}

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page, ModelMap modal) {
		modal.addAttribute("riscos", RiscoCliente.values());
		return page;
	}

}
