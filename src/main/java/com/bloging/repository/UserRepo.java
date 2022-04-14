package com.bloging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
