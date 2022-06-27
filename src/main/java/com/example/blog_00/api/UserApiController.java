package com.example.blog_00.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_00.dto.ResponseDto;
import com.example.blog_00.model.User;
import com.example.blog_00.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> saveUser(User user) {
		int result = userService.saveUser(user);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result);
	}

}
