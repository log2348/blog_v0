package com.example.blog_00.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_00.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
