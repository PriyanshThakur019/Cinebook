
package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.RequestDTO.BulkHallSeatRequestDTO;
import com.priyansh.cinebook.DTO.RequestDTO.HallSeatItemRequestDTO;
import com.priyansh.cinebook.DTO.RequestDTO.HallSeatRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallSeatResponseDTO;
import com.priyansh.cinebook.Entity.HallDetails;
import com.priyansh.cinebook.Entity.HallSeats;
import com.priyansh.cinebook.Mapper.HallSeatMapper;
import com.priyansh.cinebook.Repository.HallDetailsRepository;
import com.priyansh.cinebook.Repository.HallSeatsRepository;
import com.priyansh.cinebook.Repository.TheatreToHallMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HallSeatsService {

    private final HallSeatsRepository seatRepository;
    private final HallDetailsRepository hallRepository;
    private final HallSeatMapper seatMapper;
    private final TheatreToHallMappingRepository mappingRepository;

    @Transactional
    public HallSeatResponseDTO addSeat(HallSeatRequestDTO request) {

        HallDetails hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Hall not found")
                );

        if (!Boolean.TRUE.equals(hall.getIsActive())) {
            throw new IllegalArgumentException("Hall is inactive");
        }

        boolean isHallAssigned = mappingRepository
                .existsByHallDetails_HallDetailsIdAndIsActiveTrue(
                        request.getHallId()
                );

        if (!isHallAssigned) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot add seats. Hall is not assigned to any theatre."
            );
        }

        boolean seatExists = seatRepository
                .existsByHallDetails_HallDetailsIdAndSeatNumber(
                        request.getHallId(),
                        request.getSeatNumber().trim().toUpperCase()
                );

        if (seatExists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Seat number already exists in this hall."
            );
        }

        HallSeats seat = seatMapper.toEntity(request);
        seat.setHallDetails(hall);

        LocalDateTime now = LocalDateTime.now();
        seat.setCreatedDate(now);
        seat.setUpdatedDate(now);
        seat.setIsActive(true);

        return seatMapper.toResponseDTO(
                seatRepository.save(seat)
        );
    }

    @Transactional(readOnly = true)
    public List<HallSeatResponseDTO> getSeatsByHall(Long hallId) {

        return seatRepository
                .findByHallDetails_HallDetailsIdAndIsActiveTrue(hallId)
                .stream()
                .map(seatMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public long getActiveSeatCount(Long hallId) {

        return seatRepository
                .countByHallDetails_HallDetailsIdAndIsActiveTrue(hallId);
    }

    @Transactional
    public List<HallSeatResponseDTO> addSeatsInBulk(
            BulkHallSeatRequestDTO request) {

        HallDetails hall = hallRepository.findById(request.getHallId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Hall not found")
                );

        if (!Boolean.TRUE.equals(hall.getIsActive())) {
            throw new IllegalArgumentException("Hall is inactive");
        }

        boolean isHallAssigned = mappingRepository
                .existsByHallDetails_HallDetailsIdAndIsActiveTrue(
                        request.getHallId()
                );

        if (!isHallAssigned) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot add seats. Hall is not assigned to any theatre."
            );
        }

        List<HallSeatItemRequestDTO> requestedSeats = request.getSeats();

        if (requestedSeats == null || requestedSeats.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one seat must be provided"
            );
        }

        // Check duplicate seat numbers within the incoming request
        Set<String> seatNumbers = requestedSeats.stream()
                .map(seat -> seat.getSeatNumber().trim().toUpperCase())
                .collect(Collectors.toSet());

        if (seatNumbers.size() != requestedSeats.size()) {
            throw new IllegalArgumentException(
                    "Duplicate seat numbers found in request"
            );
        }

        // Check whether any requested seat already exists in the database
        for (String seatNumber : seatNumbers) {
            if (seatRepository
                    .existsByHallDetails_HallDetailsIdAndSeatNumber(
                            request.getHallId(), seatNumber)) {

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Seat already exists: " + seatNumber
                );
            }
        }

        // Check remaining hall capacity
        long existingSeatCount = seatRepository
                .countByHallDetails_HallDetailsIdAndIsActiveTrue(
                        request.getHallId()
                );

        if (existingSeatCount + requestedSeats.size() > hall.getHallCapacity()) {
            throw new IllegalArgumentException(
                    "Requested seats exceed remaining hall capacity"
            );
        }

        LocalDateTime now = LocalDateTime.now();

        List<HallSeats> seatsToSave = requestedSeats.stream()
                .map(seatRequest -> {
                    HallSeats seat = new HallSeats();

                    seat.setHallDetails(hall);
                    seat.setSeatNumber(
                            seatRequest.getSeatNumber().trim().toUpperCase()
                    );
                    seat.setSeatType(seatRequest.getSeatType());
                    seat.setIsActive(true);
                    seat.setCreatedDate(now);
                    seat.setUpdatedDate(now);

                    return seat;
                })
                .toList();

        return seatRepository.saveAll(seatsToSave)
                .stream()
                .map(seatMapper::toResponseDTO)
                .toList();
    }
}
