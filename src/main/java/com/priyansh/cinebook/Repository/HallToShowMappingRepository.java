package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.HallToShowMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HallToShowMappingRepository
        extends JpaRepository<HallToShowMapping, Long> {

    List<HallToShowMapping> findByHallDetails_HallDetailsIdAndIsActiveTrue(
            Long hallId
    );

    Optional<HallToShowMapping> findByShowDetails_ShowDetailId(
            Long showDetailId
    );
}
