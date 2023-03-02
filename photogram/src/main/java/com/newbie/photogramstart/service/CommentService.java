package com.newbie.photogramstart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newbie.photogramstart.domain.comment.Comment;
import com.newbie.photogramstart.domain.comment.CommentRepository;
import com.newbie.photogramstart.domain.image.Image;
import com.newbie.photogramstart.domain.user.User;
import com.newbie.photogramstart.domain.user.UserRepository;
import com.newbie.photogramstart.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	
	@Transactional
	public Comment 댓글쓰기(String content,int imageId,int userId) {
		
		//Tip(객체를 만들 때 id값만 담아서 insert할 수 있다.)
		//대신 return 시에 image객체와 user객체는 id값만 담아서 가져오기 때문에 구체적인 정보가 필요할 땐 이렇게하면 안된다.
		Image image = new Image();
		image.setId(imageId);
		
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
		});
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setImage(image);
		comment.setUser(userEntity);
		
		return commentRepository.save(comment);
	}
	
	@Transactional
	public void 댓글삭제() {
		
	}
	
}
