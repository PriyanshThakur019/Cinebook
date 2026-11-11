
package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.RequestDTO.HallToShowMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallToShowMappingResponseDTO;
import com.priyansh.cinebook.Entity.HallDetails;
import com.priyansh.cinebook.Entity.HallToShowMapping;
import com.priyansh.cinebook.Entity.ShowDetails;
import com.priyansh.cinebook.Mapper.HallToShowMappingMapper;
import com.priyansh.cinebook.Repository.HallDetailsRepository;
import com.priyansh.cinebook.Repository.HallToShowMappingRepository;
import com.priyansh.cinebook.Repository.ShowDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HallToShowMappingService {

    private final HallToShowMappingRepository mappingRepository;
    private final HallDetailsRepository hallRepository;
    private final ShowDetailsRepo showRepository;
    private final HallToShowMappingMapper mappingMapper;

    @Transactional
    public HallToShowMappingResponseDTO assignShowToHall(
            HallToShowMappingRequestDTO request) {

        HallDetails hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Hall not found")
                );

        ShowDetails show = showRepository
                .findById(request.getShowDetailId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Show not found")
                );

        if (!Boolean.TRUE.equals(hall.getIsActive())) {
            throw new IllegalArgumentException("Hall is inactive");
        }

        if (mappingRepository
                .findByShowDetails_ShowDetailId(request.getShowDetailId())
                .isPresent()) {
            throw new IllegalArgumentException(
                    "This screening is already assigned to a hall"
            );
        }

        HallToShowMapping mapping = mappingMapper.toEntity(request);

        mapping.setHallDetails(hall);
        mapping.setShowDetails(show);

        LocalDateTime now = LocalDateTime.now();
        mapping.setCreatedDate(now);
        mapping.setUpdatedDate(now);
        mapping.setIsActive(true);

        return mappingMapper.toResponseDTO(
                mappingRepository.save(mapping)
        );
    }

    @Transactional(readOnly = true)
    public List<HallToShowMappingResponseDTO> getShowsByHall(Long hallId) {

        return mappingRepository
                .findByHallDetails_HallDetailsIdAndIsActiveTrue(hallId)
                .stream()
                .map(mappingMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
        public List<HallToShowMappingResponseDTO> getAllHallToShowMappings() {
        return mappingRepository
                .findAll()
                .stream()
                .map(mappingMapper::toResponseDTO)
                .toList();
    }
}
