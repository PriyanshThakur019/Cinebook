
package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallSeatItemRequestDTO {

    private String seatNumber;
    private String seatType;
}
