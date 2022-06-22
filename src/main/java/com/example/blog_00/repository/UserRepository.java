package com.example.blog_00.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_00.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// spring JPA 네이밍 전략
	User findByUsernameAndPassword(String username, String password);

}
