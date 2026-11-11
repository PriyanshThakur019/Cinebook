package com.priyansh.cinebook.Service;


import com.priyansh.cinebook.DTO.RequestDTO.TheatreRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreResponseDTO;
import com.priyansh.cinebook.Entity.TheatreDetails;
import com.priyansh.cinebook.Mapper.TheatreMapper;
import com.priyansh.cinebook.Repository.TheatreDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreService {

    private final TheatreDetailsRepository theatreRepository;
    private final TheatreMapper theatreMapper;

    @Transactional
    public TheatreResponseDTO registerTheatre(TheatreRequestDTO request) {

        TheatreDetails theatre = theatreMapper.toEntity(request);

        LocalDateTime now = LocalDateTime.now();
        theatre.setCreatedDate(now);
        theatre.setUpdatedDate(now);
        theatre.setIsActive(true);

        return theatreMapper.toResponseDTO(
                theatreRepository.save(theatre)
        );
    }

    @Transactional(readOnly = true)
    public TheatreResponseDTO getTheatreById(Long theatreId) {

        TheatreDetails theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Theatre not found: " + theatreId
                        )
                );

        return theatreMapper.toResponseDTO(theatre);
    }

    @Transactional(readOnly = true)
    public List<TheatreResponseDTO> getAllActiveTheatres() {

        return theatreRepository.findByIsActiveTrue()
                .stream()
                .map(theatreMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<TheatreResponseDTO> getActiveTheatresByCity(String city) {

        return theatreRepository.findByCityIgnoreCaseAndIsActiveTrue(city)
                .stream()
                .map(theatreMapper::toResponseDTO)
                .toList();
    }
}

