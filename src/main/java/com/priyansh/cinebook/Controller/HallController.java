
package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.RequestDTO.HallRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallResponseDTO;
import com.priyansh.cinebook.Service.HallService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping("/register")
    public HallResponseDTO registerHall(
            @RequestBody HallRequestDTO requestDTO) {

        return hallService.registerHall(requestDTO);
    }

    @GetMapping("/{hallId}")
    public HallResponseDTO getHallById(
            @PathVariable Long hallId) {

        return hallService.getHallById(hallId);
    }

    @GetMapping
    public List<HallResponseDTO> getAllHalls() {

        return hallService.getAllHalls();
    }
}
