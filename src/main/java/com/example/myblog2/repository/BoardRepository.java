package com.example.myblog2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myblog2.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}