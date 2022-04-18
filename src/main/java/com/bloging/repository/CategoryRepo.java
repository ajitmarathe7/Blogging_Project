package com.bloging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloging.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
	
}
