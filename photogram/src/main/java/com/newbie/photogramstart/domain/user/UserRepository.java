package com.newbie.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 ioc등록이 자동으로 됨(JpaRepository를 상속받아서)
public interface UserRepository extends JpaRepository<User, Integer>{
	//JPA Query method
	User findByUsername(String username);
}
