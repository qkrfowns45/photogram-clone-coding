package com.newbie.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.image.Image;
import com.newbie.photogramstart.domain.image.ImageRepository;
import com.newbie.photogramstart.domain.subscibe.SubscribeRepository;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.domain.user.UserRepository;
import com.newbie.photogramstart.web.dto.image.ImageUploadDto;
import com.newbie.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	private final UserRepository userRepository;
	
	private final SubscribeRepository subscribeRepository;
	
	@Transactional(readOnly = true)
	public List<Image> 인기사진(){
		return imageRepository.mPopular();
	}
	
	@Transactional(readOnly = true) //영속성 컨텍스트에서 변경 감지를 해서, 더티체킹, flush(반영) X
	public Page<Image> 이미지스토리(int principalId,Pageable pageable){
		Page<Image> images = imageRepository.mStroy(principalId,pageable);
		
		//images에 좋아요 상태 담기
		images.forEach((image)->{
			
			image.setLikeCount(image.getLikes().size());
			
			image.getLikes().forEach((like)->{
				if(like.getUser().getId() == principalId) { //해당 이미지에 좋아요한 사람들을 찾아서 현재 로그인한 사람이 좋아요 한것인지 비교
					image.setLikeState(true);
				}
			});
			
		});
		return images;
	}
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void 사진업로드(ImageUploadDto imageUploadDto,PrincipalDetails principalDetails) {
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); //유일한 파일명
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		// 통신, I/O -> 예외가 발생할 수 있따.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
		imageRepository.save(image);
		
	}
	
	@Transactional(readOnly = true) //영속성 컨텍스트에서 변경 감지를 해서, 더티체킹, flush(반영) X
	public List<UserProfileDto> 유저검색(String content,int principalId){
		
		List<UserProfileDto> users = new ArrayList<>();
		
		List<User> user = userRepository.userSearch(content);
		for(int i=0; i<user.size(); i++) {
			UserProfileDto dto = new UserProfileDto();
			int pageUserid = user.get(i).getId();
			
			dto.setUser(user.get(i));
			dto.setPageOwnerState(pageUserid == principalId); //1은 페이지주인, -1은 주인아닌 다른 유저
			
			int subscribeState =  subscribeRepository.mSubscribeState(principalId, pageUserid);
			int subscribeCount =  subscribeRepository.mSubscribeCount(principalId);
			
			dto.setSubscribeState(subscribeState == 1);
			dto.setSubscribeCount(subscribeCount);
			users.add(dto);
		}
		
		return users;
	}
}
