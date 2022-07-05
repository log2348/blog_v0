package com.example.myblog2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myblog2.auth.PrincipalDetail;
import com.example.myblog2.dto.RequestFileDto;
import com.example.myblog2.model.Image;
import com.example.myblog2.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@GetMapping("/home")
	public String storyHome(Model model,
			@PageableDefault(size = 10, sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Image> imagePage = storyService.getImageList(pageable);
		model.addAttribute("imagePage", imagePage);
		return "/story/home";
	}

	@GetMapping("/upload_form")
	public String uploadStoryForm() {
		return "/story/upload_form";
	}
	
	@PostMapping("/image/upload")
	public String uploadStoryImage(RequestFileDto fileDto,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		
		storyService.uploadImage(fileDto, principalDetail.getUser());
		return "redirect:/story/home";
	}
	
}
