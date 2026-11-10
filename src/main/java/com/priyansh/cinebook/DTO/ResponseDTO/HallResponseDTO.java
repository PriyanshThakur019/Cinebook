package com.priyansh.cinebook.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallResponseDTO {

    private Long hallDetailsId;
    private String hallName;
    private Integer hallCapacity;
    private String hallType;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
