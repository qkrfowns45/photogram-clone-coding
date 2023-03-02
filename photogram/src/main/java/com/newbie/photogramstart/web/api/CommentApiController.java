package com.newbie.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.comment.Comment;
import com.newbie.photogramstart.service.CommentService;
import com.newbie.photogramstart.web.dto.CMRespDto;
import com.newbie.photogramstart.web.dto.comment.CommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

	private final CommentService commentService;
	
	@PostMapping("/api/comment")
	public ResponseEntity<?> commentSave(@RequestBody CommentDto commentDto,@AuthenticationPrincipal PrincipalDetails principalDetails){//@@RequestBody를 선언해야 형식을 dto에 맞게 넣을수 있다.
		
		Comment comment = commentService.댓글쓰기(commentDto.getContent(),commentDto.getImageId(),principalDetails.getUser().getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1,"댓글쓰기성공",comment),HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> commentDelete(@PathVariable int id){
		return null;
	}
}
