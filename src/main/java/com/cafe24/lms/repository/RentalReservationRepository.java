package com.cafe24.lms.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cafe24.lms.domain.Item;
import com.cafe24.lms.domain.RentalReservation;

public interface RentalReservationRepository extends JpaRepository<RentalReservation, Long> {

//	@Query(value="select r from RentalReservation r where r.item.no = :no",nativeQuery=false)
//	RentalReservation findByItemNo(@Param("no")Long no);

//	@Query(value="SELECT r.reservationOrder FROM RentalReservation r where r.item.no = :no order by r.reservationOrder desc",nativeQuery=false)
//	Integer findByItemNo(@Param("no")Long no);
	@Query(value="SELECT max(r.reservationOrder) FROM RentalReservation r where r.item.no = :no",nativeQuery=false)
	Long findRentableByItemNo(@Param("no")Long no);
	
	@Query(value="SELECT r FROM RentalReservation r where r.item.no = :itemNo and r.reservationOrder = :orderNo",nativeQuery=false)
	RentalReservation findByItemNoAndOrderNo(@Param("itemNo")Long itemNo, @Param("orderNo")Long orderNo);

	@Query(value="SELECT max(r.reservationOrder) FROM RentalReservation r where r.item.no = :itemNo and r.user.no = :userNo",nativeQuery=false)
	Long findByItemNoAndUserNo(@Param("itemNo")Long itemNo, @Param("userNo")Long userNo);
	
	@Query(value="SELECT r FROM RentalReservation r where r.reservationOrder = 0",nativeQuery=false)
	List<RentalReservation> findByCurrentRental();

	@Query(value="SELECT r FROM RentalReservation r where r.reservationOrder = 0",nativeQuery=false)
	Page<RentalReservation> findByCurrentRental(Pageable pageable);
	
	@Query(value="SELECT r FROM RentalReservation r where r.reservationOrder > 0",nativeQuery=false)
	Page<RentalReservation> findByCurrentReservation(Pageable pageable);

}
