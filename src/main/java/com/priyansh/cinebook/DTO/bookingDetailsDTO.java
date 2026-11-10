package com.priyansh.cinebook.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class bookingDetailsDTO {
    private Integer bookingId;
    @NonNull
    private Long showDetailId;
    @NonNull
    private String userName;
    @NonNull
    private BigDecimal BookingCost;

    private String SeatNo;
}
