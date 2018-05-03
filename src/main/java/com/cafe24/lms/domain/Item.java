package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="DTYPE")
public abstract class Item {
	@Id
	@Column(name="ITEM_NO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	private String title;
	@ManyToOne
	@JoinColumn(name = "CATEGORY_NO")
	private Category category;
	@Transient
    private String categoryName;
	@Transient
    private boolean rentable;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public boolean isRentable() {
		return rentable;
	}
	public void setRentable(boolean rentable) {
		this.rentable = rentable;
	}
	@Override
	public String toString() {
		return "Item [no=" + no + ", title=" + title + ", category=" + category + ", categoryName=" + categoryName
				+ ", rentable=" + rentable + "]";
	}
	
}
