package com.example.myblog2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myblog2.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	// SELECT * FROM board WHERE title LIKE %q%
	Page<Board> findByTitleContaining(String title, Pageable pageable);

}
