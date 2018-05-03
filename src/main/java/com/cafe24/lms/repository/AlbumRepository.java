package com.cafe24.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafe24.lms.domain.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
