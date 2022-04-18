package com.bloging.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.payloads.PostDto;
import com.bloging.services.PostService;


@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("User/{userId}/Category/{categoryId}")
	public PostDto createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) {
		
		
		PostDto newPost =	this.postService.createPost(postDto, userId, categoryId);
		
		return newPost;
		
	};
	
	
	@PostMapping("post/{postId}")
	public PostDto updatePost(@Valid @RequestBody PostDto postDto ,@PathVariable Integer postId) {
		
		
		PostDto updatedPostDto = this.updatePost(postDto, postId);
		
		return updatedPostDto;
		
	}
	
}
