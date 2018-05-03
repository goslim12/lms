package com.cafe24.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.DVD;
import com.cafe24.lms.repository.DVDRepository;

@Service
@Transactional()
public class DVDService {
	@Autowired
	private DVDRepository dvdRepository;  
	
	
	
	public DVD find(DVD dvd) {
		return dvdRepository.findOne(dvd.getNo());
	}
	public List<DVD> findAll() {
		return dvdRepository.findAll();
	}
	public void insert(DVD dvd) {
		dvdRepository.save(dvd);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(DVD dvd) {
		dvdRepository.delete(dvd.getNo());
	}
}
