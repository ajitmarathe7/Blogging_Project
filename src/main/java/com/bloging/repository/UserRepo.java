package com.bloging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloging.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

}
