package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.ShowDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowDetailsRepo extends JpaRepository<ShowDetails, Long> {

    Optional<ShowDetails> findFirstByShowName(String showName);

    boolean existsByShowName(String showName);

    @Query(value = "SELECT nextval('\"ShowIdSequence\"')", nativeQuery = true)
    Long generateNextShowId();

    Optional<ShowDetails> findByShowDetailId(Long showDetailId);
}
