package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
