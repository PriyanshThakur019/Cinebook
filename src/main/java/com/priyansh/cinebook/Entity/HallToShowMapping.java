package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "\"HallToShowMapping\"",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UQ_HallToShow_ShowDetail",
                        columnNames = {"ShowDetailId"}
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallToShowMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"HallToShowMappingId\"")
    private Long hallToShowMappingId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"HallId\"",
            referencedColumnName = "\"HallDetailsId\"",
            nullable = false
    )
    private HallDetails hallDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "\"ShowDetailId\"",
            referencedColumnName = "\"ShowDetailId\"",
            nullable = false
    )
    private ShowDetails showDetails;

    @Column(name = "\"CreatedDate\"", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "\"UpdatedDate\"", nullable = false)
    private LocalDateTime updatedDate;

    @Builder.Default
    @Column(name = "\"IsActive\"", nullable = false)
    private Boolean isActive = true;
}
