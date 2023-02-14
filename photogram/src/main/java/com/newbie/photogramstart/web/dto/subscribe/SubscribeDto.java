package com.newbie.photogramstart.web.dto.subscribe;

import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.web.dto.user.UserProfileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

	private int id;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;
	private Integer equalUserState;
}
