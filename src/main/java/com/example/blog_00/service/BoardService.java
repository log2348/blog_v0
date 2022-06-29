package com.example.blog_00.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog_00.dto.BoardSaveRequestDto;
import com.example.blog_00.model.Board;
import com.example.blog_00.model.User;
import com.example.blog_00.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void savePost(BoardSaveRequestDto dto) {
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardRepository.save(boardEntity);
	}

	// 글 작성 기능
	@Transactional
	public void saveBoard(Board board, User user) {
		board.setUserId(user);
		boardRepository.save(board);		
	}

}
