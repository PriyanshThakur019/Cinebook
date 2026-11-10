package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "\"TheatreToHallMapping\"",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_TheatreToHall_Hall",
                        columnNames = {"HallId"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreToHallMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"TheatreToHallMappingId\"")
    private Long theatreToHallMappingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"TheatreId\"",
            referencedColumnName = "\"TheatreDetailId\"",
            nullable = false
    )
    private TheatreDetails theatreDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"HallId\"",
            referencedColumnName = "\"HallDetailsId\"",
            nullable = false
    )
    private HallDetails hallDetails;

    @Column(name = "\"CreatedDate\"", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "\"UpdatedDate\"", nullable = false)
    private LocalDateTime updatedDate;

    @Builder.Default
    @Column(name = "\"IsActive\"", nullable = false)
    private Boolean isActive = true;
}
