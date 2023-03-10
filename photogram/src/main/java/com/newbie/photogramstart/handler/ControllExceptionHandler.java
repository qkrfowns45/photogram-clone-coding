package com.newbie.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.handler.ex.CustomApiException;
import com.newbie.photogramstart.handler.ex.CustomException;
import com.newbie.photogramstart.handler.ex.CustomValidationApiException;
import com.newbie.photogramstart.handler.ex.CustomValidationException;
import com.newbie.photogramstart.util.Script;
import com.newbie.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		//CMRespDto, Script 비교
		//1.클라이언트에게 응답할 때는 Script 좋음
		//2.Ajax통신 - CMRespDto
		//3.Android 통신 - CMRespDto
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}
		return Script.back(e.getErrorMap().toString());
		//return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
	}
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);
	}
}
