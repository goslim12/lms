package com.cafe24.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Album;
import com.cafe24.lms.repository.AlbumRepository;

@Service
@Transactional()
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;  
	
	
	
	public Album find(Album album) {
		return albumRepository.findOne(album.getNo());
	}
	public List<Album> findAll() {
		return albumRepository.findAll();
	}
	public void insert(Album album) {
		albumRepository.save(album);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(Album album) {
		albumRepository.delete(album.getNo());
	}
}
