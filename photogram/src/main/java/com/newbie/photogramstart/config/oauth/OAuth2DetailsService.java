package com.newbie.photogramstart.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.newbie.photogramstart.config.auth.PrincipalDetails;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		Map<String, Object> userInfo = oAuth2User.getAttributes();

		String username = "facebook_"+(String)userInfo.get("id");
		String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
		String name = (String)userInfo.get("name"); 
		String email = (String)userInfo.get("email");
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) { //페이스북 최초 로그인
		
			User user = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.name(name)
					.build();
			
			return new PrincipalDetails(userRepository.save(user));
			
		}else { //페이스북으로 이미 회원가입이 되어 있다는 뜻
			return new PrincipalDetails(userEntity);
		}
		
		
	}
}
