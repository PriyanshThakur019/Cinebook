
package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.RequestDTO.TheatreRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreResponseDTO;
import com.priyansh.cinebook.Service.TheatreService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping("/register")
    public TheatreResponseDTO registerTheatre(
            @RequestBody TheatreRequestDTO requestDTO) {

        return theatreService.registerTheatre(requestDTO);
    }

    @GetMapping("/{theatreId}")
    public TheatreResponseDTO getTheatreById(
            @PathVariable Long theatreId) {

        return theatreService.getTheatreById(theatreId);
    }

    @GetMapping
    public List<TheatreResponseDTO> getAllActiveTheatres() {

        return theatreService.getAllActiveTheatres();
    }

    @GetMapping("/city/{city}")
    public List<TheatreResponseDTO> getTheatresByCity(
            @PathVariable String city) {

        return theatreService.getActiveTheatresByCity(city);
    }
}
