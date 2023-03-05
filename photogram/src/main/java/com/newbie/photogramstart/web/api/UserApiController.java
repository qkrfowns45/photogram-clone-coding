package com.newbie.photogramstart.web.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.handler.ex.CustomValidationApiException;
import com.newbie.photogramstart.handler.ex.CustomValidationException;
import com.newbie.photogramstart.service.SubscribeService;
import com.newbie.photogramstart.service.UserService;
import com.newbie.photogramstart.web.dto.CMRespDto;
import com.newbie.photogramstart.web.dto.subscribe.SubscribeDto;
import com.newbie.photogramstart.web.dto.user.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	private final SubscribeService subscribeService;
	
	@PutMapping("/api/user/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId,MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		User user = userService.회원프로필사진변경(principalId,profileImageFile);
		principalDetails.setUser(user);
		return new ResponseEntity<>(new CMRespDto<>(1,"프로필사진변경 성공",null),HttpStatus.OK);
	}
	
	@GetMapping("/api/user/{pageUserId}/subscribe")
	public ResponseEntity<?> subscribeList(@PathVariable int pageUserId,@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		List<SubscribeDto> subscibeDto = subscribeService.구독리스트(principalDetails.getUser().getId(),pageUserId);
		
		return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 가져오기 성공",subscibeDto),HttpStatus.OK);
	}
	
	@PutMapping("/api/user/{id}")
	public CMRespDto<?> update(
			@PathVariable int id,  
			@Valid UserUpdateDto updateDto,
			BindingResult bindingResult, //꼭 @Valid가 적혀있는 다음 파라미터에 적는다.
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		
			User userEntity = userService.회원수정(id, updateDto.toEntity());
			principalDetails.setUser(userEntity);
			return new CMRespDto<>(1,"회원수정완료",userEntity); //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
	}
}
