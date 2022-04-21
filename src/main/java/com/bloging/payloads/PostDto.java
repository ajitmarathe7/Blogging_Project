package com.bloging.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bloging.entities.Category;
import com.bloging.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer Id;
	@NotEmpty
	@Size(min = 3 , message = "Size of title must be greater than 3 characters")
	private String title;
	@NotEmpty
	@Size(min = 10 , message = "Size of content must be greater than 10 characters")
	private String content;
	private Date addedDate;
	private String imageName;
	private UserDto user;
	private CategoryDto category;
	

}
