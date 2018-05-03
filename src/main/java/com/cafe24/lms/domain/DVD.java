package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("D")
public class DVD extends Item {
	private String distributor;

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "DVD [distributor=" + distributor + ", getCategory()=" + getCategory() + ", getNo()=" + getNo()
				+ ", getTitle()=" + getTitle() + "]";
	}
	
}
