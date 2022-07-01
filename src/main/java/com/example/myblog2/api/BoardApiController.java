package com.example.myblog2.api;

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

import com.example.myblog2.auth.PrincipalDetail;
import com.example.myblog2.dto.ResponseDto;
import com.example.myblog2.model.Board;
import com.example.myblog2.model.Reply;
import com.example.myblog2.service.BoardService;

@RestController
@RequestMapping("/api")
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	// 글 작성 기능
	// 작성한 유저 정보까지 보내기
	@PostMapping("/board/save")
	public ResponseDto<Integer> saveBoard(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
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
	
	// 댓글 작성 기능
	@PostMapping("/board/{boardId}/reply")
	public ResponseDto<Reply> saveReply(@PathVariable int boardId, @RequestBody Reply reply,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		
		Reply replyEntity = boardService.saveReply(principalDetail.getUser(), boardId, reply);		
		return new ResponseDto<>(HttpStatus.OK.value(), replyEntity);
	}
	
	// 댓글 삭제 기능
	@DeleteMapping("/board/{boardId}/reply/{replyId}")
	public void deleteReply(@PathVariable int boardId, @PathVariable int replyId) {
		boardService.deleteReplyById(replyId);
	}
}
