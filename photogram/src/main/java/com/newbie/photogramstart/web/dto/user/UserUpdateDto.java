package com.newbie.photogramstart.web.dto.user;

import javax.validation.constraints.NotBlank;

import com.newbie.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	@NotBlank
	private String name;
	@NotBlank
	private String password;
	private String website;
	private String bio;
	private String phone;
	private String gender;

	public User toEntity() {
		return User.builder()
				.name(name)
				.password(password) //패스워드를 기재안했으면 password가 공백으로 들어감 Validation체크를 해야함
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
