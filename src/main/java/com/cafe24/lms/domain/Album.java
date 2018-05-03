package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
	
	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "Album [artist=" + artist + ", getCategory()=" + getCategory() + ", getNo()=" + getNo() + ", getTitle()="
				+ getTitle() + "]";
	}
}
