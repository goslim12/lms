package com.cafe24.lms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Role;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.UserRepository;

@Service
@Transactional()
public class UserService {
	@Autowired
	private UserRepository userRepository;  
	
	
	public User findByEmailAndPassword(User user) {
		return  userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
	}
	public boolean existEmail(String email) {
		return  null != userRepository.findByEmail(email);
	}
	
	public User find(User user) {
		return userRepository.findOne(user.getNo());
	}
	public void join(User user) {
//		user.setGender(Gender.female);
		if(user.getRole()==null)
			user.setRole(Role.USER);
		userRepository.save(user);
	}
	
	public void modify(User user) {
		if(user.getRole()==null)
			user.setRole(Role.USER);
		userRepository.update(user);
	}
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(User user) {
		userRepository.delete(user.getNo());
	}
}
