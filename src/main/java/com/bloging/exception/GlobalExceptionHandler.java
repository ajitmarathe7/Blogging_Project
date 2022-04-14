package com.bloging.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bloging.payloads.ApiResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		ApiResponce apiResponce= new ApiResponce(message, false);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		
		Map<String, String > resp = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			
			String errorName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			
			resp.put(errorName, message);
			
		});
		
		return new ResponseEntity<Map<String,String>> (resp , HttpStatus.BAD_REQUEST);
	}
	
}
