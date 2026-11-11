
package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkHallSeatRequestDTO {

    private Long hallId;
    private List<HallSeatItemRequestDTO> seats;
}
