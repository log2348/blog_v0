package com.example.myblog2.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myblog2.model.RoleType;
import com.example.myblog2.model.User;
import com.example.myblog2.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class BlogTestController {
	
	@Autowired
	private UserRepository userRepository;
	
	// selectAll
	@GetMapping("/users")
	public List<User> userList() {
		return userRepository.findAll();
	}

	@GetMapping("/user")
	public Page<User> pageList(@PageableDefault(size = 2, sort = "id",
			direction = Direction.DESC) Pageable pageable) {
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage;
	}
	
	@GetMapping("user/{id}")
	public User userDetail(@PathVariable int id) {
		
//		Optional<User> result = userRepository.findById(id); // Null safety
//		User user = userRepository.findById(id).get(); // Null safety
		
//		User user = userRepository.findById(id).orElseGet(() -> {
//			return new User();
//		});
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException(id + "에 해당하는 유저는 존재하지 않습니다.");
		});
		
		return user;
	}
	
	
	// 회원 가입
	// create User
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "[ " + user.getUsername() + " ] 님의 회원가입이 완료되었습니다.";
	}
	
	@Transactional
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser) {
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException(id + "에 해당하는 유저는 존재하지 않습니다.");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return id + "에 해당하는 유저는 존재하지 않습니다.";
		}
		return "[ id : " + id + " ] 유저 삭제";
	}

}
