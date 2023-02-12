package com.newbie.photogramstart.service;

import java.util.function.Supplier;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.photogramstart.domain.subscibe.SubscribeRepository;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.domain.user.UserRepository;
import com.newbie.photogramstart.handler.ex.CustomException;
import com.newbie.photogramstart.handler.ex.CustomValidationApiException;
import com.newbie.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscribeRepository subscribeRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserProfileDto 회원프로필(int pageUserid,int principalId) {
		
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserid).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 없는 페이지 입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserid == principalId); //1은 페이지주인, -1은 주인아닌 다른 유저
		dto.setImageCount(userEntity.getImages().size());
		
		int subscribeState =  subscribeRepository.mSubscribeState(principalId, pageUserid);
		int subscribeCount =  subscribeRepository.mSubscribeCount(principalId);
		
		dto.setSubscribeState(subscribeState == 1);
		dto.setSubscribeCount(subscribeCount);
		
		return dto;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		//1.영속화
		User userEntity = userRepository.findById(id).orElseThrow(() -> {return new CustomValidationApiException("찾을 수 없는 id입니다.");});
		
		//2.영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		userEntity.setPassword(encPassword);
		userEntity.setWebsite(user.getWebsite());
		userEntity.setBio(user.getBio());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	}
}
