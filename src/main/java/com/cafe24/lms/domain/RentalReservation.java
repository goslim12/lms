package com.cafe24.lms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rental_reservation")
public class RentalReservation {	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	@Column(name="rent_date")
	private Date rentDate;
	@Column(name="return_date")
	private Date returnDate;
	@Column(name="reservation_order")
	private Long reservationOrder;
	@ManyToOne
	@JoinColumn(name = "ITEM_NO")
	private Item item;	
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private User user;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Long getReservationOrder() {
		return reservationOrder;
	}

	public void setReservationOrder(Long reservationOrder) {
		this.reservationOrder = reservationOrder;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RentalReservation [no=" + no + ", rentDate=" + rentDate + ", returnDate=" + returnDate
				+ ", reservationOrder=" + reservationOrder + ", item=" + item + ", user=" + user + "]";
	}

}
