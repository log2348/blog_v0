package com.example.blog_00.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog_00.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {
		model.addAttribute("pageable", boardService.getBoardList(pageable));
		return "index";
	}

	// 글 작성 폼
	@GetMapping("/board/save_post_form")
	public String getSavePostForm() {
		return "board/save_post_form";
	}
	
	// 글 상세보기 폼
	@GetMapping("/board/{id}")
	public String getDetailPostForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.getBoardDetail(id));
		return "board/detail_post_form";
	}
	
	// 글 수정 폼
	@GetMapping("/board/update_post_form/{id}")
	public String getUpdatePostForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.getBoardDetail(id));
		return "board/update_post_form";
	}
	
}
