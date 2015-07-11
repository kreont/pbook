package com.sandbox.phonebook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// Единственная ссылка - на домашнюю страницу
public class HomeController {
	@RequestMapping("/home")
	public String home() {
		return "index";
	}
}