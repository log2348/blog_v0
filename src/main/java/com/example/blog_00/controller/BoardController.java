package com.example.blog_00.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}

	// 글 작성 폼
	@GetMapping("/board/save_post_form")
	public String getSavePostForm() {
		return "board/save_post_form";
	}
	
	
}
