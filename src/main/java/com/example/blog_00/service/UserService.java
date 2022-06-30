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
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	// 회원정보 수정 기능
	@Transactional
	public void updateUser(User user) {

		User userEntity = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("존재하지 않는 회원입니다.");
		});
		
		// 패스워드 해싱
		String rawPassword = user.getPassword();
		String hashPassword = encoder.encode(rawPassword);
		
		userEntity.setPassword(hashPassword);
		userEntity.setEmail(user.getEmail());
		
	}

	@Transactional
	public User checkOldUser(String username) {
		User userEntity = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		
		return userEntity;
	}

}
