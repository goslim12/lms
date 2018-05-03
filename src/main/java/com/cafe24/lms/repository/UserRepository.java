package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByEmailAndPassword(String Email,String password);
	
//	@Modifying
//	@Query(value="update Users u set u.name=:name where u.no=:no",nativeQuery=false)
//	int update(@Param("name") String name,
//			@Param("no") Long no);
	
	
	@Modifying
	@Query(value="update User u set u.name=:#{#user.name},u.gender=:#{#user.gender},u.password=:#{#user.password} where u.no=:#{#user.no}",nativeQuery=false)
	int update(@Param("user")User user);
	
	
}
