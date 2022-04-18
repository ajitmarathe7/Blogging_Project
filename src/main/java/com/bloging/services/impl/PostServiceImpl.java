package com.bloging.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloging.entities.Category;
import com.bloging.entities.Post;
import com.bloging.entities.User;
import com.bloging.exception.ResourceNotFoundException;
import com.bloging.payloads.CategoryDto;
import com.bloging.payloads.PostDto;
import com.bloging.payloads.UserDto;
import com.bloging.repository.CategoryRepo;
import com.bloging.repository.PostRepo;
import com.bloging.repository.UserRepo;
import com.bloging.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User ID ", userId));
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category ID", categoryId));
		
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		
		Post post = this.modelMapper.map(postDto, Post.class);
		
		post.setCategory(category);
		post.setUser(user);
		post.setAddedDate(new Date());
		post.setImageName("Defualt.png");
		
		
		Post createdPost = this.postRepo.save(post);
		PostDto createdPostDto = this.modelMapper.map(createdPost, PostDto.class);
		createdPostDto.setCategoryDto(categoryDto);
		createdPostDto.setUserDto(userDto);
		return createdPostDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post ID ", postId));
		
		Post updatedPost = this.modelMapper.map(postDto, Post.class);
		
		post.setTitle(updatedPost.getTitle());
		post.setContent(updatedPost.getContent());
		
		
		Post newPost = this.postRepo.save(post);
		
		PostDto updatedDto = this.modelMapper.map(newPost, PostDto.class);
		
		
		
		
		// TODO Auto-generated method stub
		return updatedDto;
	}

	@Override
	public PostDto getSinglePost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void deleteSinglePost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
