package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallToShowMappingRequestDTO {

    private Long hallId;
    private Long showDetailId;
}
