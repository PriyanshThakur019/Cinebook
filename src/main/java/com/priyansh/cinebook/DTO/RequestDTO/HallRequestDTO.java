package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallRequestDTO {

    private String hallName;
    private Integer hallCapacity;
    private String hallType;
}