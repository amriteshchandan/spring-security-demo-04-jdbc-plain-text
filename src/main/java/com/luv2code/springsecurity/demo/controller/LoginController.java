package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(path = "/showMyLoginPage", method = RequestMethod.GET)
	public String showMyLoginPage() {
//		return "plain-login";
		return "fancy-login";
	}
	
	@RequestMapping(path = "/access-denied", method = RequestMethod.GET)
	public String showAccessDeniedPage() {
		return "access-denied";
	}
	
}
