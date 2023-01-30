package com.newbie.photogramstart.web.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newbie.photogramstart.web.dto.user.UserUpdateDto;

@RestController
public class UserApiController {

	@PutMapping("/api/user/{id}")
	public String update(UserUpdateDto updateDto) {
		System.out.println(updateDto);
		return "ok";
	}
}
