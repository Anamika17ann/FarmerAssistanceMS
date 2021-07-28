package com.farm.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FarmingExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> exception(UserNotFoundException exception) {
		return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MyFarmingException.class)
	public ResponseEntity<Object> exception(MyFarmingException exception) {
		return new ResponseEntity<>("User Bad request", HttpStatus.BAD_REQUEST);
	}
}
