package com.newbie.photogramstart.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.comment.Comment;
import com.newbie.photogramstart.handler.ex.CustomValidationApiException;
import com.newbie.photogramstart.service.CommentService;
import com.newbie.photogramstart.web.dto.CMRespDto;
import com.newbie.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

	private final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto,BindingResult bindingResult,@AuthenticationPrincipal PrincipalDetails principalDetails){//@@RequestBody를 선언해야 형식을 dto에 맞게 넣을수 있다.
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error: bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new CustomValidationApiException("유효성 검사 실패함",errorMap);
		}
		
		Comment comment = commentService.댓글쓰기(commentDto.getContent(),commentDto.getImageId(),principalDetails.getUser().getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기성공",comment),HttpStatus.OK);
	}
	
	@DeleteMapping("/api/comment/{id}")
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		
		commentService.댓글삭제(id);
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글삭제성공",null),HttpStatus.OK);
	}
}
