package com.example.myblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myblog2.dto.BoardSaveRequestDto;
import com.example.myblog2.model.Board;
import com.example.myblog2.model.Reply;
import com.example.myblog2.model.User;
import com.example.myblog2.repository.BoardRepository;
import com.example.myblog2.repository.ReplyRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
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

	@Transactional
	public Reply saveReply(User user, int boardId, Reply requestReply) {
		// 먼저 boardId로 Board 객체 찾는다
		Board boardEntity = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("해당 게시글은 존재하지 않습니다.");
		});
		
		requestReply.setUser(user);
		requestReply.setBoard(boardEntity);

		Reply replyEntity = replyRepository.save(requestReply);
		
		return replyEntity;
	}

	@Transactional
	public void deleteReplyById(int replyId) {
		replyRepository.deleteById(replyId);		
	}
	
	// 검색 기능
	@Transactional(readOnly = true)
	public Page<Board> searchBoardByTitle(String title, Pageable pageable) {
		return boardRepository.findByTitleContaining(title, pageable);
	}

}
