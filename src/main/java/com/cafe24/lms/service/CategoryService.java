package com.cafe24.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Category;
import com.cafe24.lms.repository.CategoryRepository;

@Service
@Transactional()
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;  
	
	
	
	public Category find(Category category) {
		return categoryRepository.findOne(category.getNo());
	}
	public Category find(Long no) {
		return categoryRepository.findOne(no);
	}
	public void insert(Category category) {
		categoryRepository.save(category);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(Category category) {
		categoryRepository.delete(category.getNo());
	}
}
