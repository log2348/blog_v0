package com.example.blog_00.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Transactional
	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Board getBoardDetail(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void deleteById(int id) {
		boardRepository.deleteById(id);		
	}

	@Transactional
	public void updateBoard(int id, Board board) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글 찾을 수 없습니다.");
		});
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
	}

}
