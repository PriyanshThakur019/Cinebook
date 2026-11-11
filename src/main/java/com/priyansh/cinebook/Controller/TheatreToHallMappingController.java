
package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.RequestDTO.TheatreToHallMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.TheatreToHallMappingResponseDTO;
import com.priyansh.cinebook.Service.TheatreToHallMappingService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre-hall-mapping")
@RequiredArgsConstructor
public class TheatreToHallMappingController {

    private final TheatreToHallMappingService mappingService;

    @PostMapping
    public TheatreToHallMappingResponseDTO assignHallToTheatre(
            @RequestBody TheatreToHallMappingRequestDTO requestDTO) {

        return mappingService.assignHallToTheatre(requestDTO);
    }

    @GetMapping("/theatre/{theatreId}")
    public List<TheatreToHallMappingResponseDTO> getHallsByTheatre(
            @PathVariable Long theatreId) {

        return mappingService.getHallsByTheatre(theatreId);
    }
}
