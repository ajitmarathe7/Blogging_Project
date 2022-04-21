package com.bloging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloging.entities.Category;
import com.bloging.entities.Post;
import com.bloging.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

List<Post> findByUser(User user);
List<Post> findByCategory(Category category);
	
}
