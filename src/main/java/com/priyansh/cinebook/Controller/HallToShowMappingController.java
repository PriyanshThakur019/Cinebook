
package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.RequestDTO.HallToShowMappingRequestDTO;
import com.priyansh.cinebook.DTO.ResponseDTO.HallToShowMappingResponseDTO;
import com.priyansh.cinebook.Service.HallToShowMappingService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall-show-mapping")
@RequiredArgsConstructor
public class HallToShowMappingController {

    private final HallToShowMappingService mappingService;

    @PostMapping
    public HallToShowMappingResponseDTO assignShowToHall(
            @RequestBody HallToShowMappingRequestDTO requestDTO) {

        return mappingService.assignShowToHall(requestDTO);
    }

    @GetMapping("/hall/{hallId}")
    public List<HallToShowMappingResponseDTO> getShowsByHall(
            @PathVariable Long hallId) {

        return mappingService.getShowsByHall(hallId);
    }

    @GetMapping("/findAllHallToShowMappings")
    public List<HallToShowMappingResponseDTO> getAllHallToShowMappings() {
        return mappingService.getAllHallToShowMappings();
    }
}
