package com.cafe24.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Book;
import com.cafe24.lms.repository.BookRepository;

@Service
@Transactional()
public class BookService {
	@Autowired
	private BookRepository bookRepository;  
	
	
	
	public Book find(Book book) {
		return bookRepository.findOne(book.getNo());
	}
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	public void insert(Book book) {
		bookRepository.save(book);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(Book book) {
		bookRepository.delete(book.getNo());
	}
}
