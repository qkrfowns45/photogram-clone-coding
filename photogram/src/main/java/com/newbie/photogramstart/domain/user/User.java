package com.newbie.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.newbie.photogramstart.domain.image.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 20, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	private String website; //웹 사이트
	private String bio; //자기소개
	private String phone;
	private String gender;
	
	private String profileImageUrl;
	private String role;
	
	//나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼을 만들지 않는다.
	//User를 Select할 때 해당 User id로 등록된 image들을 다 가져온다.
	//Lazy = User를 Select할 때 해당 User id로 등록된 image를 안가져온다. - 대신 getImages를 호출할때만 가져온다.
	//Eager = User를 Select할 때 해당 User id로 등록된 image를 join해서 가져온다.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) 
	private List<Image> images; //양방향 매핑
	
	private LocalDateTime createDate;
	
	@PrePersist //디비에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
