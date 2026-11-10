package com.priyansh.cinebook.DTO;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingDetailsDTO {
    private Integer bookingId;
    @NonNull
    private Long showDetailId;
    @NonNull
    private String username;
    @NonNull
    private BigDecimal BookingCost;

    private String SeatNo;
}
