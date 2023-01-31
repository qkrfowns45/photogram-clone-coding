package com.newbie.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.handler.ex.CustomValidationApiException;
import com.newbie.photogramstart.handler.ex.CustomValidationException;
import com.newbie.photogramstart.service.UserService;
import com.newbie.photogramstart.web.dto.CMRespDto;
import com.newbie.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id,  
			@Valid UserUpdateDto updateDto,
			BindingResult bindingResult, //꼭 @Valid가 적혀있는 다음 파라미터에 적는다.
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
		}else {
			User userEntity = userService.회원수정(id, updateDto.toEntity());
			principalDetails.setUser(userEntity);
			
			return new CMRespDto<>(1,"회원수정완료",userEntity);
		}
	}
}
