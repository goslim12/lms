package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
