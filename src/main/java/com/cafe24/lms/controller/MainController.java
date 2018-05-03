package com.cafe24.lms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.lms.domain.Album;
import com.cafe24.lms.domain.Book;
import com.cafe24.lms.domain.Category;
import com.cafe24.lms.domain.DVD;
import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentalReservation;
import com.cafe24.lms.domain.Role;
import com.cafe24.lms.domain.User;
import com.cafe24.lms.service.AlbumService;
import com.cafe24.lms.service.BookService;
import com.cafe24.lms.service.CategoryService;
import com.cafe24.lms.service.DVDService;
import com.cafe24.lms.service.ItemService;
import com.cafe24.lms.service.RentalReservationService;
import com.cafe24.lms.service.UserService;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DVDService dvdService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private BookService bookService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private RentalReservationService rentalReservationService;
	@RequestMapping( { "", "/main" ,"/{path1}"} )
	public String index( Model model,
			@PathVariable("path1") Optional<Long> page) {
		Map<String,Object> map = null;
		
		if(page.isPresent()) {
//			itemService.findPage(0)이 1페이지 호출이므로 빼기
			map = itemService.findPage(page.get().intValue()-1);
		}
		else {
			map = itemService.findPage(0);
		}
//		@SuppressWarnings("unchecked")
		List<Item> list = (List<Item>)map.get("list");
		for(Item item :list) {
			item.setCategoryName(item.getCategory().getName());
			//rentalReservationRepository.findByItemNo(item.getNo()) 호출시
			// null 대여가능
			// 0 일 경우 현재 대여중
			// 1 일 경우 예약순위 1까지 있음
			// 2 일 경우 예약순위 2까지 있음
			if(rentalReservationService.findRentableByItemNo(item.getNo())==null)
				item.setRentable(true);
		}
		model.addAttribute("list", list);
		model.addAttribute("totalPages",map.get("totalPages"));
		model.addAttribute("pageSize",map.get("pageSize"));
		model.addAttribute("currentPage",map.get("currentPage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("pageNumPerBlock",map.get("pageNumPerBlock"));
		model.addAttribute("getTotalElements",map.get("getTotalElements"));
		return "main/index";
	}
	

	
	@Auth()
	@RequestMapping( "/rent" )
	public String rent( 
			Model model,
			@AuthUser User user,
			@RequestParam(value="no")Long itemNo) {
		Long order = rentalReservationService.findRentableByItemNo(itemNo);
		RentalReservation rentalReservation = new RentalReservation();
		rentalReservation.setItem(itemService.find(itemNo));
		rentalReservation.setUser(user);
//			rentalReservation.setUser(userService.find(user));
		Date rentableDay = null;
		Date returnDay = null;
		if(order==null) {
			rentableDay = new Date();
			returnDay = new Date(rentableDay.getTime() + 86400000*7);
			rentalReservation.setReservationOrder(0L);
		}else {
			Long num = rentalReservationService.findByItemNoAndUserNo(itemNo,user.getNo());
			if(num!=null) //num이 null이 아니면 현재 예약중이거나 대여중인 상태
				return "main/alreadyRent";
			
			rentableDay = rentalReservationService.findByItemNoAndOrderNo(itemNo,order).getReturnDate();
			rentalReservation.setReservationOrder(order+1);
			returnDay = new Date(rentableDay.getTime() + 86400000*7);
		}
		rentalReservation.setRentDate(rentableDay);
		rentalReservation.setReturnDate(returnDay);
		rentalReservationService.insert(rentalReservation);
		return "main/rent";
	}

	@RequestMapping( "/init" )//초기화제공
	public String init() {	
// 		어드민 관리자 생성!!!!!!!!!!!!!!!
		User admin = new User();
		admin.setEmail("admin");
		admin.setGender("male");
		admin.setName("admin");
		admin.setPassword("admin");
		admin.setRole(Role.ADMIN);
		userService.join(admin);

//		사용자 생성		
		User user = new User();
		user.setEmail("123");
		user.setGender("female");
		user.setName("123");
		user.setPassword("123");
		user.setRole(Role.USER);
		userService.join(user);
		
//		카테고리 추가!!!!!!
		List<Category> list = new ArrayList<Category>();
		for(int i=0;i<9;i++) {
			list.add(new Category());
		}
		list.get(0).setName("도서(소설)");
		list.get(1).setName("도서(IT)");
		list.get(2).setName("도서(수필)");
		list.get(3).setName("음반(락)");
		list.get(4).setName("음반(클래식)");
		list.get(5).setName("음반(K-POP)");
		list.get(6).setName("DVD(영화)");
		list.get(7).setName("DVD(드라마)");
		list.get(8).setName("DVD(애니)");
		for(Category cat :list) {
			categoryService.insert(cat);
		}
		//DVD추가
		DVD dvd1 = new DVD();
		dvd1.setCategory(categoryService.find(7L));
		dvd1.setDistributor("사기꾼엔터테이먼트");
		dvd1.setTitle("저만 믿으세요");
		dvdService.insert(dvd1);
		
		DVD dvd2 = new DVD();
		dvd2.setCategory(categoryService.find(8L));
		dvd2.setDistributor("장편엔터테이먼트");
		dvd2.setTitle("미니시리즈가 좋아");
		dvdService.insert(dvd2);
		
		DVD dvd3 = new DVD();
		dvd3.setCategory(categoryService.find(9L));
		dvd3.setDistributor("사나이엔터테이먼트");
		dvd3.setTitle("여성시대");
		dvdService.insert(dvd3);
		
		Album album1 = new Album();
		album1.setCategory(categoryService.find(4L));
		album1.setArtist("김효은");
		album1.setTitle("New Kings");
		albumService.insert(album1);
		
		Album album2 = new Album();
		album2.setCategory(categoryService.find(5L));
		album2.setArtist("김경한");
		album2.setTitle("여자가 제일좋아");
		albumService.insert(album2);
		
		Album album3 = new Album();
		album3.setCategory(categoryService.find(6L));
		album3.setArtist("여자");
		album3.setTitle("경한이가 제일좋아");
		albumService.insert(album3);
		
		Book book1 = new Book();
		book1.setCategory(categoryService.find(1L));
		book1.setIsbn("111111111");
		book1.setTitle("자소서");
		bookService.insert(book1);
		
		Book book2 = new Book();
		book2.setCategory(categoryService.find(2L));
		book2.setIsbn("2222222");
		book2.setTitle("토비의 스프링");
		bookService.insert(book2);
		
		Book book3 = new Book();
		book3.setCategory(categoryService.find(3L));
		book3.setIsbn("3333333");
		book3.setTitle("일기장");
		bookService.insert(book3);
		
		return "redirect:/";
	}
	
}
