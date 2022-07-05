package com.example.myblog2.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myblog2.dto.RequestFileDto;
import com.example.myblog2.model.Image;
import com.example.myblog2.model.User;
import com.example.myblog2.repository.StoryRepsitory;

@Service
public class StoryService {

	@Value("${file.path}")
	private String uploadFolder;
	
	@Autowired
	private StoryRepsitory storyRepsitory;
	
	@Transactional
	public Page<Image> getImageList(Pageable pageable) {
		return storyRepsitory.findAll(pageable);
	}
	
	@Transactional
	public void uploadImage(RequestFileDto fileDto, User user) {
		/**
		 * 
		 * 파일 업로드 기능 (해당 서버에 파일 생성하고 성공하면 DB 저장)
		 */
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + "story"; // 한글이름 파일 저장시 오류 방지
		String newFileName = (imageFileName.trim()).replaceAll("\\s", "");
		
		Path imageFilePath = Paths.get(uploadFolder + newFileName);
		
		try {
			// 서버에 파일 생성
			Files.write(imageFilePath, fileDto.getFile().getBytes());
			
			// DB 저장
			Image imageEntity = fileDto.toEntity(newFileName, user);
			storyRepsitory.save(imageEntity);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
