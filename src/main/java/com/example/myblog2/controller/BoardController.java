package com.example.myblog2.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myblog2.model.Board;
import com.example.myblog2.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(@PageableDefault(size = 4, sort = "id", direction = Direction.DESC) Pageable pageable,
			Model model) {
		
		// Pagination - 페이지 블록 동적 처리
		
		Page<Board> boardPages = boardService.getBoardList(pageable);
		
		// 현재 화면 페이지
		int nowPage = boardPages.getPageable().getPageNumber() + 1;
		
		// 현재 화면에 보여질 페이지 블록의 시작 번호
		int startPage = Math.max(nowPage - 2, 1);
		
		// 현재 화면에 보여질 페이지 블록의 마지막 번호
		int endPage = Math.min(nowPage + 2, boardPages.getTotalPages());
		
		// 페이지 번호 배열로 만들기
		ArrayList<Integer> pageNumbers = new ArrayList<Integer>();
		
		for (int i = startPage; i < endPage + 1; i++) {
			pageNumbers.add(i);
		}
		
		model.addAttribute("pageable", boardPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("pageNumbers", pageNumbers);
		
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
