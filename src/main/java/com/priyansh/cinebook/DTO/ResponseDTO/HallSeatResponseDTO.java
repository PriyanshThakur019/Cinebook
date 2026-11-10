package com.priyansh.cinebook.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallSeatResponseDTO {

    private Long hallSeatId;
    private Long hallId;
    private String seatNumber;
    private String seatType;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
