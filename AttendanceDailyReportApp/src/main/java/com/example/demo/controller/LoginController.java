package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public String showLoginForm() {
		return "login";
	}
	
//	@PostMapping
//	public String login(@RequestParam String loginId, @RequestParam String password, HttpSession session, Model model) {
//		Optional<User> userOpt = userRepository.findByLoginId(loginId);
//		System.out.println("Here 1");
//
//		if(userOpt.isPresent()) {
//			User user = userOpt.get();
//			System.out.println("Here 2");
//			if(passwordEncoder.matches(password, user.getPassword())) {
//				System.out.println("Here 3");
//
//			session.setAttribute("user", user);
//			return "redirect:/home";
//			}
//		}
//		model.addAttribute("error", "ログインIDまたはパスワードが間違っています");
//		return "login";
//		
//	}

}
