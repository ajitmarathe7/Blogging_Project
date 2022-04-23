package com.bloging.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bloging.entities.User;
import com.bloging.exception.ResourceNotFoundException;
import com.bloging.payloads.UserDto;
import com.bloging.repository.UserRepo;
import com.bloging.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		//convert DtoUser to entity user
		User user = this.dtoToUser(userDto); 
		
		User savedUser= userRepo.save(user);
		
		//convert saved user to Dto user
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser =  userRepo.save(user);
		
		UserDto userDto2 = this.userToDto(updatedUser); 
		
		return userDto2;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
		
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		
		List <UserDto> userDtos =users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userID) {
		
		// TODO Auto-generated method stub
		userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User","UserId",userID));
		
		this.userRepo.deleteById(userID);
		
		
		//this.userRepo.delete(user);
		
	}

	public User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		
	//	user.setId(userDto.getId());
	//	user.setName(userDto.getName());
	//	user.setEmail(userDto.getEmail());
	//	user.setPassword(userDto.getPassword());
	//	user.setAbout(userDto.getAbout());

		return user;

	}

	public UserDto userToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		
		
		//	userDto.setId(user.getId());
	//	userDto.setName(user.getName());
	//	userDto.setEmail(user.getEmail());
	//	userDto.setPassword(user.getPassword());
	//	userDto.setAbout(user.getAbout());

		return userDto;

	}

}
