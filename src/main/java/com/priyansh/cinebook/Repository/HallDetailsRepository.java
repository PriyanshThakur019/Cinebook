package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.HallDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallDetailsRepository
        extends JpaRepository<HallDetails, Long> {
}