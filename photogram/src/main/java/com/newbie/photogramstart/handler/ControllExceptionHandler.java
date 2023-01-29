package com.newbie.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
		
		return Script.back(e.getErrorMap().toString());
		//return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
	}
}
