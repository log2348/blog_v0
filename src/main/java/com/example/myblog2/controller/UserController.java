package com.example.myblog2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myblog2.dto.ResponseDto;
import com.example.myblog2.model.User;
import com.example.myblog2.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/auth/login_form")
	public String loginForm() {
		return "user/login_form";
	}
	
	@GetMapping("/auth/join_form")
	public String joinForm() {
		return "user/join_form";
	}
	
	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
		return "redirect:/";
	}
	
	// 회원가입 후 메인 페이지로 리다이렉트
	@PostMapping("/auth/joinProc")
	public String saveUser(User user) {
		userService.saveUser(user);
		return "redirect:/";
	}
	
	// 회원정보 수정 폼 호출
	@GetMapping("/user/update_user_form")
	public String updateUserForm() {
		return "user/update_user_form";
	}
	
}
