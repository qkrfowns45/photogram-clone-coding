package com.newbie.photogramstart.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.image.Image;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.handler.ex.CustomValidationException;
import com.newbie.photogramstart.service.ImageService;
import com.newbie.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService;
	
	@GetMapping({"/","/image/story"})
	public String story() {
		return "image/story";
	}
	
	@GetMapping("/image/popular")
	public String popular(Model model) {
		
		List<Image> images = imageService.인기사진();
		
		model.addAttribute("images",images);
		
		return "image/popular";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image")
	public String imageUpload(ImageUploadDto imageUploadDto,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		//서비스 호출
		
		if(imageUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
		}
		imageService.사진업로드(imageUploadDto, principalDetails);
		
		return "redirect:/user/"+principalDetails.getUser().getId();
	}
	
	@GetMapping("/image/search")
	public String search(HttpServletRequest request, Model model) {
		
		String res = (String)request.getParameter("type");
		String content = (String)request.getParameter("search");
		List<User> user = null;
		
		if(res.equals("T")) {
			
		}else if(res.equals("C")) {
			
		}else if(res.equals("W")) {
			user = imageService.유저검색(content);
		}
		
		return "image/search";
	}
}
