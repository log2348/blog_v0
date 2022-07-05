package com.example.myblog2.dto;

import org.springframework.web.multipart.MultipartFile;

import com.example.myblog2.model.Image;
import com.example.myblog2.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFileDto {

	private MultipartFile file;
	private String uuid;
	private String imageTitle;
	
	public Image toEntity(String storyImageUrl, User user) {
		return Image.builder()
				.imageTitle(imageTitle)
				.imageUrl(storyImageUrl)
				.originFileName(file.getOriginalFilename())
				.user(user)
				.build();
	}
}
