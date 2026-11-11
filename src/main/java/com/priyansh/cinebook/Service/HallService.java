package com.priyansh.cinebook.Service;


import com.priyansh.cinebook.DTO.RequestDTO.HallRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallResponseDTO;
import com.priyansh.cinebook.Entity.HallDetails;
import com.priyansh.cinebook.Mapper.HallMapper;
import com.priyansh.cinebook.Repository.HallDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {

    private final HallDetailsRepository hallRepository;
    private final HallMapper hallMapper;

    @Transactional
    public HallResponseDTO registerHall(HallRequestDTO request) {

        if (request.getHallCapacity() == null
                || request.getHallCapacity() <= 0) {
            throw new IllegalArgumentException(
                    "Hall capacity must be greater than zero"
            );
        }

        HallDetails hall = hallMapper.toEntity(request);

        LocalDateTime now = LocalDateTime.now();
        hall.setCreatedDate(now);
        hall.setUpdatedDate(now);
        hall.setIsActive(true);

        return hallMapper.toResponseDTO(
                hallRepository.save(hall)
        );
    }

    @Transactional(readOnly = true)
    public HallResponseDTO getHallById(Long hallId) {

        HallDetails hall = hallRepository.findById(hallId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Hall not found: " + hallId
                        )
                );

        return hallMapper.toResponseDTO(hall);
    }

    @Transactional(readOnly = true)
    public List<HallResponseDTO> getAllHalls() {

        return hallRepository.findAll()
                .stream()
                .map(hallMapper::toResponseDTO)
                .toList();
    }
}

