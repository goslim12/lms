package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Category {
	@Id
	@Column(name="CATEGORY_NO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	private String name;

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [no=" + no + ", name=" + name + "]";
	}
	
}
