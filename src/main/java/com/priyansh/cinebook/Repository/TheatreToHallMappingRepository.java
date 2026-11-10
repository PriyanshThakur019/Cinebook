package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.TheatreToHallMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreToHallMappingRepository
        extends JpaRepository<TheatreToHallMapping, Long> {

    List<TheatreToHallMapping> findByTheatreDetails_TheatreDetailIdAndIsActiveTrue(
            Long theatreId
    );

    Optional<TheatreToHallMapping> findByHallDetails_HallDetailsId(
            Long hallId
    );
}
