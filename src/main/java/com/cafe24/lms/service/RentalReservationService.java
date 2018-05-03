package com.cafe24.lms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentalReservation;
import com.cafe24.lms.repository.RentalReservationRepository;

@Service
@Transactional()
public class RentalReservationService {
	@Autowired
	private RentalReservationRepository rentalReservationRepository;  
	
	
	
	public RentalReservation find(RentalReservation rentalReservation) {
		return rentalReservationRepository.findOne(rentalReservation.getNo());
	}
	public RentalReservation findByItemNoAndOrderNo(Long itemNo,Long orderNo) {
		return rentalReservationRepository.findByItemNoAndOrderNo(itemNo,orderNo);
	}
	public Long findRentableByItemNo(Long no) {
		return rentalReservationRepository.findRentableByItemNo(no);
	}
	public Long findByItemNoAndUserNo(Long itemNo,Long userNo) {
		return rentalReservationRepository.findByItemNoAndUserNo(itemNo,userNo);
	}
	public List<RentalReservation> findAll() {
		return rentalReservationRepository.findAll();
	}
	public List<RentalReservation> findByCurrentRental() {
		return rentalReservationRepository.findByCurrentRental();
	}
	public Map<String,Object> findByCurrentRental(int pageNum) {
		PageRequest pageRequest = new PageRequest(pageNum, 5);
		Page<RentalReservation> result = rentalReservationRepository.findByCurrentRental(pageRequest);
		
		List<RentalReservation> items = result.getContent(); //조회된 데이터
		int totalPages = result.getTotalPages(); //전체 페이지 수
		int pageSize = result.getSize(); //전체 페이지 수
		int currentPage = result.getNumber()+1; //현재 페이지
		Long getTotalElements = result.getTotalElements(); //현재 페이지
		int startPage = ((currentPage-1)/5)*5+1; //현재 페이지
		int pageNumPerBlock = 5;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", items);
		map.put("totalPages", totalPages);
		map.put("getSize", totalPages);
		map.put("currentPage", currentPage);
		map.put("startPage", startPage);
		map.put("pageNumPerBlock", pageNumPerBlock);
		map.put("getTotalElements", getTotalElements);
		return map;
	}
	
	public Map<String,Object> findByCurrentReservation(int pageNum) {
		PageRequest pageRequest = new PageRequest(pageNum, 5);
		Page<RentalReservation> result = rentalReservationRepository.findByCurrentReservation(pageRequest);
		
		List<RentalReservation> items = result.getContent(); //조회된 데이터
		int totalPages = result.getTotalPages(); //전체 페이지 수
		int pageSize = result.getSize(); //전체 페이지 수
		int currentPage = result.getNumber()+1; //현재 페이지
		Long getTotalElements = result.getTotalElements(); //현재 페이지
		int startPage = ((currentPage-1)/5)*5+1; //현재 페이지
		int pageNumPerBlock = 5;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", items);
		map.put("totalPages", totalPages);
		map.put("getSize", totalPages);
		map.put("currentPage", currentPage);
		map.put("startPage", startPage);
		map.put("pageNumPerBlock", pageNumPerBlock);
		map.put("getTotalElements", getTotalElements);
		return map;
	}
	public void insert(RentalReservation rentalReservation) {
		rentalReservationRepository.save(rentalReservation);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(RentalReservation rentalReservation) {
		rentalReservationRepository.delete(rentalReservation.getNo());
	}
}
