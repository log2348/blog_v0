package com.example.blog_00.dto;

import com.example.blog_00.model.Board;

import lombok.Data;

@Data
public class BoardSaveRequestDto {

	private String title;
	private String content;
	
	public static Board toEntity(BoardSaveRequestDto dto) {
		Board boardEntity = Board.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return boardEntity;
	}
}
