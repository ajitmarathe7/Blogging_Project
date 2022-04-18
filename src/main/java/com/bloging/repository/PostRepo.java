package com.bloging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloging.entities.Post;
import com.bloging.payloads.CategoryDto;
import com.bloging.payloads.PostDto;
import com.bloging.payloads.UserDto;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

List<PostDto> findByUser(UserDto userdto);
List<PostDto> findByCategory(CategoryDto categoryDto);
	
}
