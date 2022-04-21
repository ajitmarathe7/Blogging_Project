package com.bloging.payloads;

//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryDto {

	
	private Integer id;
	
	@NotEmpty
	@Size(min = 3, message = "Category must be minimum of 3 characters !!")
	private String categoryTitle;
			
	@NotEmpty
	private String categoryDescription;
	
	
	
	
}
