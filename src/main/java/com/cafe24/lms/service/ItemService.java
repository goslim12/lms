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
import com.cafe24.lms.repository.ItemRepository;

@Service
@Transactional()
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;  
	
	
	
	public Item find(Item item) {
		return itemRepository.findOne(item.getNo());
	}
	public Item find(Long no) {
		return itemRepository.findOne(no);
	}
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	public Map<String,Object> findPage(int pageNum) {
		PageRequest pageRequest = new PageRequest(pageNum, 5);
		Page<Item> result = itemRepository.findPage(pageRequest);
		
		List<Item> items = result.getContent(); //조회된 데이터
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
//		boolean hasNextPage = result.hasNextPage(); //다음 페이지 존재 여부
		return map;
	}
	public void insert(Item item) {
		itemRepository.save(item);
	}
	
//	public Guestbook insertMessage2(Guestbook guestBookVo) {
//		Guestbook vo = null;
//		guestbookRepository.save(guestBookVo);
//		vo = guestbookRepository.get(guestBookVo.getNo());
//		return vo;
//	}
	public void delete(Item item) {
		itemRepository.delete(item.getNo());
	}
}
