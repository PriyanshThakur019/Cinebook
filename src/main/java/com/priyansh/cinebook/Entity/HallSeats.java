package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "\"HallSeats\"",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_HallSeats_Hall_Seat",
                        columnNames = {"HallDetailsId", "SeatNumber"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"HallSeatId\"")
    private Long hallSeatId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"HallDetailsId\"",
            referencedColumnName = "\"HallDetailsId\"",
            nullable = false
    )
    private HallDetails hallDetails;

    @Column(name = "\"SeatNumber\"", nullable = false, length = 10)
    private String seatNumber;

    @Column(name = "\"SeatType\"", length = 30)
    private String seatType;

    @Builder.Default
    @Column(name = "\"IsActive\"", nullable = false)
    private Boolean isActive = true;

    @Column(name = "\"CreatedDate\"", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "\"UpdatedDate\"", nullable = false)
    private LocalDateTime updatedDate;
}
