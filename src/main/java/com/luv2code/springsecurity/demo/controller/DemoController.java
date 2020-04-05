package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String showHome() {
		return "home";
	}
	
	@RequestMapping(path = "/leaders", method = RequestMethod.GET)
	public String showLeaders() {
		return "leaders";
	}
	
	@RequestMapping(path = "/systems", method = RequestMethod.GET)
	public String showSystems() {
		return "systems";
	}
	
}
