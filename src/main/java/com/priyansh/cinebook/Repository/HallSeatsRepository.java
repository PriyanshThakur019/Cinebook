package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.HallSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallSeatsRepository
        extends JpaRepository<HallSeats, Long> {

    List<HallSeats> findByHallDetails_HallDetailsIdAndIsActiveTrue(
            Long hallId
    );

    long countByHallDetails_HallDetailsIdAndIsActiveTrue(Long hallId);
}
