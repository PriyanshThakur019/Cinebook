package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreToHallMappingRequestDTO {

    private Long theatreId;
    private Long hallId;
}
