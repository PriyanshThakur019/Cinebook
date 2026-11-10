package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Long> {

    Optional<List<BookingDetails>> findByUserName(String userName);

    boolean existsByBookingId(Long bookingId);

}
