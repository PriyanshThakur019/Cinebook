
package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.RequestDTO.BulkHallSeatRequestDTO;
import com.priyansh.cinebook.DTO.RequestDTO.HallSeatRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallSeatResponseDTO;
import com.priyansh.cinebook.Service.HallSeatsService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall-seats")
@RequiredArgsConstructor
public class HallSeatsController {

    private final HallSeatsService hallSeatsService;

    @PostMapping
    public HallSeatResponseDTO addSeat(
            @RequestBody HallSeatRequestDTO requestDTO) {

        return hallSeatsService.addSeat(requestDTO);
    }

    @GetMapping("/hall/{hallId}")
    public List<HallSeatResponseDTO> getSeatsByHall(
            @PathVariable Long hallId) {

        return hallSeatsService.getSeatsByHall(hallId);
    }

    @GetMapping("/hall/{hallId}/count")
    public long getActiveSeatCount(
            @PathVariable Long hallId) {

        return hallSeatsService.getActiveSeatCount(hallId);
    }

    @PostMapping("/bulk")
    public List<HallSeatResponseDTO> addSeatsInBulk(
            @RequestBody BulkHallSeatRequestDTO requestDTO) {

        return hallSeatsService.addSeatsInBulk(requestDTO);
    }
}
