package com.newbie.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice
public class ControllExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public Map<String,String> validationException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
