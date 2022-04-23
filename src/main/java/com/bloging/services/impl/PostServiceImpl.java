package com.bloging.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bloging.entities.Category;
import com.bloging.entities.Post;
import com.bloging.entities.User;
import com.bloging.exception.ResourceNotFoundException;
import com.bloging.payloads.PostDto;
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
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID ", userId));
		// UserDto userDto = this.modelMapper.map(user, UserDto.class);

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		// CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);

		Post post = this.modelMapper.map(postDto, Post.class);

		post.setCategory(category);
		post.setUser(user);
		post.setAddedDate(new Date());
		post.setImageName("Defualt.png");

		Post createdPost = this.postRepo.save(post);
		PostDto createdPostDto = this.modelMapper.map(createdPost, PostDto.class);
		// createdPostDto.setCategory(categoryDto);
		// createdPostDto.setUser(userDto);

		return createdPostDto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());

		Post updatedPost = this.postRepo.save(post);

		System.out.println(post.getCategory());

		return this.modelMapper.map(updatedPost, PostDto.class);

	}

	@Override
	public PostDto getSinglePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post ", "Post Id ", postId));
		// TODO Auto-generated method stub

		return this.modelMapper.map(post, PostDto.class);

	}

	@Override
	public void deleteSinglePost(Integer postId) {

		// TODO Auto-generated method stub

		this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

		this.postRepo.deleteById(postId);

	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", categoryId));

		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		// TODO Auto-generated method stub
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));

		List<Post> posts = this.postRepo.findByUser(user);

		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		// TODO Auto-generated method stub
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize,String sort) {		
		
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort));

		Page<Post> page= this.postRepo.findAll(pageable);
		
		
		List<Post> posts = page.getContent();
		
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		// TODO Auto-generated method stub
		return postDtos;
	}
}
