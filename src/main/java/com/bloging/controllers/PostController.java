package com.bloging.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.payloads.ApiResponce;
import com.bloging.payloads.PostDto;
import com.bloging.services.PostService;


@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("User/{userId}/Category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) {
		
		
		PostDto newPost =	this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(newPost,HttpStatus.CREATED);
		
	};
	
	
	@PutMapping("post/{postId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto ,@PathVariable Integer postId) {
		
		
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
		
	}

	
	@GetMapping("post/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
		
		PostDto postDto = this.postService.getSinglePost(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable Integer postId){
		
		this.postService.deleteSinglePost(postId);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("Post deleted Successfully ",true), HttpStatus.OK);
	
	}
	
}
