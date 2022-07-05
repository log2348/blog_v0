package com.example.myblog2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myblog2.model.Image;

public interface StoryRepsitory extends JpaRepository<Image, Integer>{

}
