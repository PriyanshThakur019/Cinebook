package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"TheatreDetails\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"TheatreDetailId\"")
    private Long theatreDetailId;

    @Column(name = "\"TheatreOwner\"", length = 150)
    private String theatreOwner;

    @Column(name = "\"TheatreName\"", nullable = false, length = 150)
    private String theatreName;

    @Column(name = "\"TheatreAddress\"", nullable = false, length = 500)
    private String theatreAddress;

    @Column(name = "\"City\"", nullable = false, length = 100)
    private String city;

    @Column(name = "\"State\"", nullable = false, length = 100)
    private String state;

    @Column(name = "\"Pincode\"", nullable = false, length = 10)
    private String pincode;

    @Column(name = "\"ContactNumber\"", length = 20)
    private String contactNumber;

    @Column(name = "\"ContactEmail\"", length = 150)
    private String contactEmail;

    @Column(name = "\"CreatedDate\"", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "\"UpdatedDate\"", nullable = false)
    private LocalDateTime updatedDate;

    @Builder.Default
    @Column(name = "\"IsActive\"", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "theatreDetails", fetch = FetchType.LAZY)
    private List<TheatreToHallMapping> hallMappings;
}
