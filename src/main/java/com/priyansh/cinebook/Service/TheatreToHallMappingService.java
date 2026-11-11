package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.RequestDTO.TheatreToHallMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreToHallMappingResponseDTO;
import com.priyansh.cinebook.Entity.HallDetails;
import com.priyansh.cinebook.Entity.TheatreDetails;
import com.priyansh.cinebook.Entity.TheatreToHallMapping;
import com.priyansh.cinebook.Mapper.TheatreToHallMappingMapper;
import com.priyansh.cinebook.Repository.HallDetailsRepository;
import com.priyansh.cinebook.Repository.TheatreDetailsRepository;
import com.priyansh.cinebook.Repository.TheatreToHallMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreToHallMappingService {

    private final TheatreToHallMappingRepository mappingRepository;
    private final TheatreDetailsRepository theatreRepository;
    private final HallDetailsRepository hallRepository;
    private final TheatreToHallMappingMapper mappingMapper;

    @Transactional
    public TheatreToHallMappingResponseDTO assignHallToTheatre(
            TheatreToHallMappingRequestDTO request) {

        TheatreDetails theatre = theatreRepository
                .findById(request.getTheatreId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Theatre not found")
                );

        HallDetails hall = hallRepository
                .findById(request.getHallId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Hall not found")
                );

        if (!Boolean.TRUE.equals(theatre.getIsActive())
                || !Boolean.TRUE.equals(hall.getIsActive())) {
            throw new IllegalArgumentException(
                    "Theatre and hall must be active"
            );
        }

        if (mappingRepository
                .findByHallDetails_HallDetailsId(request.getHallId())
                .isPresent()) {
            throw new IllegalArgumentException(
                    "Hall is already assigned to a theatre"
            );
        }

        TheatreToHallMapping mapping = mappingMapper.toEntity(request);

        mapping.setTheatreDetails(theatre);
        mapping.setHallDetails(hall);

        LocalDateTime now = LocalDateTime.now();
        mapping.setCreatedDate(now);
        mapping.setUpdatedDate(now);
        mapping.setIsActive(true);

        return mappingMapper.toResponseDTO(
                mappingRepository.save(mapping)
        );
    }

    @Transactional(readOnly = true)
    public List<TheatreToHallMappingResponseDTO> getHallsByTheatre(
            Long theatreId) {

        return mappingRepository
                .findByTheatreDetails_TheatreDetailIdAndIsActiveTrue(theatreId)
                .stream()
                .map(mappingMapper::toResponseDTO)
                .toList();
    }
}

