package com.priyansh.cinebook.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreToHallMappingResponseDTO {

    private Long theatreToHallMappingId;
    private Long theatreId;
    private Long hallId;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
