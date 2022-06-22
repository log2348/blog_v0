package com.example.blog_00.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog_00.model.User;
import com.example.blog_00.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public int saveUser(User user) {
		try {
			System.out.println("UserService 호출 !!");
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
