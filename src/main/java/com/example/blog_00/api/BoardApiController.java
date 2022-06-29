package com.example.blog_00.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_00.auth.PrincipalDetail;
import com.example.blog_00.dto.ResponseDto;
import com.example.blog_00.model.Board;
import com.example.blog_00.service.BoardService;

@RestController
@RequestMapping("/api")
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	// 글 작성기능
	// 작성한 유저 정보까지 보내기
	@PostMapping("/board/save")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		boardService.saveBoard(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	// 글 삭제하기
	@DeleteMapping("/board/{id}")
	public ResponseDto<Integer> deleteBoard(@PathVariable int id) {
		boardService.deleteById(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/board/{id}")
	public ResponseDto<Integer> updateBoard(@PathVariable int id, @RequestBody Board board) {
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
