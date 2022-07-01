package com.example.myblog2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myblog2.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// spring JPA 네이밍 전략
	User findByUsernameAndPassword(String username, String password);
	
	Optional<User> findByUsername(String username);

}
