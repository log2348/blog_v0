package com.example.blog_00.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog_00.model.RoleType;
import com.example.blog_00.model.User;
import com.example.blog_00.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public int saveUser(User user) {
		try {
			System.out.println("UserService 호출 !!");
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			//System.out.println("rawPassword:" + rawPassword);
			//System.out.println(encPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

}
