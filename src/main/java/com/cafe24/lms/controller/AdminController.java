package com.cafe24.lms.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.lms.domain.RentalReservation;
import com.cafe24.lms.service.RentalReservationService;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

// @Auth(value=Auth.Role.ADMIN)
@Controller
@RequestMapping( "/admin" )
public class AdminController {
	@Autowired
	private RentalReservationService rentalReservationService;
	
	@Auth(Role.ADMIN)
	@RequestMapping( { "", "/rent/{path1}", "/main" } )
		public String main( Model model,
				@PathVariable("path1") Optional<Long> page) {
			Map<String,Object> map = null;
			
			if(page.isPresent()) {
//				itemService.findPage(0)이 1페이지 호출이므로 빼기
				map = rentalReservationService.findByCurrentRental(page.get().intValue()-1);
			}
			else {
				map = rentalReservationService.findByCurrentRental(0);
			}
//			@SuppressWarnings("unchecked")
			List<RentalReservation> list = (List<RentalReservation>)map.get("list");
			
			model.addAttribute("list", list);
			model.addAttribute("totalPages",map.get("totalPages"));
			model.addAttribute("pageSize",map.get("pageSize"));
			model.addAttribute("currentPage",map.get("currentPage"));
			model.addAttribute("startPage",map.get("startPage"));
			model.addAttribute("pageNumPerBlock",map.get("pageNumPerBlock"));
			model.addAttribute("getTotalElements",map.get("getTotalElements"));
			return "admin/rent";
	}
	@Auth(Role.ADMIN)
	@RequestMapping( "/reserve" )
	public String board( Model model,
			@PathVariable("path1") Optional<Long> page) {
		Map<String,Object> map = null;
		
		if(page.isPresent()) {
//			itemService.findPage(0)이 1페이지 호출이므로 빼기
			map = rentalReservationService.findByCurrentReservation(page.get().intValue()-1);
		}
		else {
			map = rentalReservationService.findByCurrentReservation(0);
		}
		
//		@SuppressWarnings("unchecked")
		List<RentalReservation> list = (List<RentalReservation>)map.get("list");
		
		model.addAttribute("list", list);
		model.addAttribute("totalPages",map.get("totalPages"));
		model.addAttribute("pageSize",map.get("pageSize"));
		model.addAttribute("currentPage",map.get("currentPage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("pageNumPerBlock",map.get("pageNumPerBlock"));
		model.addAttribute("getTotalElements",map.get("getTotalElements"));
		return "admin/reserve";
	}

}
