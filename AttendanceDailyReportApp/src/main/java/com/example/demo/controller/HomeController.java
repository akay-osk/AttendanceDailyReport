package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetails;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String showHomePage(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");

		model.addAttribute("loginId", user.getLoginId());
		model.addAttribute("name", user.getName());
		return "home";
	}

}
