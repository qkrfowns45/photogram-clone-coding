package com.newbie.photogramstart.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.newbie.photogramstart.domain.image.Image;
import com.newbie.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class ImageUploadDto {

	private MultipartFile file;
	private String caption;
	
	public Image toEntity(String postImageUrl,User user) {
		return Image.builder()
				.caption(caption)
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}
}
