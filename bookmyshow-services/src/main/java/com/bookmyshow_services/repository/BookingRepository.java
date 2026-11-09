package com.bookmyshow_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmyshow_services.modal.BookingDetails;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetails, Long>{

}
