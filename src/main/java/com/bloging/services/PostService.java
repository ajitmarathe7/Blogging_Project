package com.bloging.services;

import java.util.List;

import com.bloging.payloads.PostDto;

public interface PostService {

	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	PostDto getSinglePost(Integer postId);
	
	Void deleteSinglePost(Integer postId);
	
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> getAllPostsByUser(Integer userId);
	
}
