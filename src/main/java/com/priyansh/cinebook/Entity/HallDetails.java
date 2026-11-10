package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"HallDetails\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HallDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"HallDetailsId\"")
    private Long hallDetailsId;

    @Column(name = "\"HallName\"", nullable = false, length = 100)
    private String hallName;

    @Column(name = "\"HallCapacity\"", nullable = false)
    private Integer hallCapacity;

    @Column(name = "\"HallType\"", length = 50)
    private String hallType;

    @Column(name = "\"CreatedDate\"", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "\"UpdatedDate\"", nullable = false)
    private LocalDateTime updatedDate;

    @Builder.Default
    @Column(name = "\"IsActive\"", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "hallDetails", fetch = FetchType.LAZY)
    private List<TheatreToHallMapping> theatreMappings;

    @OneToMany(mappedBy = "hallDetails", fetch = FetchType.LAZY)
    private List<HallSeats> hallSeats;

    @OneToMany(mappedBy = "hallDetails", fetch = FetchType.LAZY)
    private List<HallToShowMapping> showMappings;
}
