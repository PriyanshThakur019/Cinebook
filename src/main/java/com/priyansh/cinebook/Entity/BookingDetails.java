package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "\"BookingDetails\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"BookingId\"")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"ShowDetailId\"",
            referencedColumnName = "\"ShowDetailId\"",
            nullable = false
    )
    private ShowDetails showDetails;

    @Column(name = "\"UserName\"", nullable = false, length = 50)
    private String userName;

    @Column(name = "\"BookingCost\"", precision = 10, scale = 2, nullable = false)
    private BigDecimal bookingCost;

    @Column(name = "\"SeatNo\"", nullable = false, length = 10)
    private String seatNo;
}
