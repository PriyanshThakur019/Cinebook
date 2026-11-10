package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.TheatreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreDetailsRepository
        extends JpaRepository<TheatreDetails, Long> {

    List<TheatreDetails> findByCityIgnoreCaseAndIsActiveTrue(String city);

    List<TheatreDetails> findByIsActiveTrue();
}

