package com.example.myblog2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myblog2.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@GetMapping("/home")
	public String storyHome() {
		return "/story/story_home";
	}

	@GetMapping("/upload_form")
	public String uploadStoryForm() {
		return "/story/upload_form";
	}
	
}
