package com.priyansh.cinebook.DTO.ResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallToShowMappingResponseDTO {

    private Long hallToShowMappingId;
    private Long hallId;
    private Long showDetailId;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
