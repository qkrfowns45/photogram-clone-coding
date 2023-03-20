package com.newbie.photogramstart.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//어노테이션이 없어도 ioc등록이 자동으로 됨(JpaRepository를 상속받아서)
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Query method
	User findByUsername(String username);
	
	@Query(value= "SELECT * FROM user WHERE username LIKE %:content%",nativeQuery = true)
	List<User> userSearch(String content);
}
