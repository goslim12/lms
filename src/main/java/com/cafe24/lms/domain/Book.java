package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
	private String isbn;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", getCategory()=" + getCategory() + ", getNo()=" + getNo() + ", getTitle()="
				+ getTitle() + "]";
	}

}
