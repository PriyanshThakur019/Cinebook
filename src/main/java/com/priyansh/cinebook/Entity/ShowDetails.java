package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"ShowDetails\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ShowDetailId\"")
    private Long showDetailId;

    @Column(name = "\"ShowId\"", nullable = false)
    private Long showId;

    @Column(name = "\"ShowName\"", nullable = false, length = 150)
    private String showName;

    @Column(name = "\"ShowDuration\"", nullable = false)
    private Integer showDuration;

    @Column(name = "\"ShowHost\"", nullable = false, length = 150)
    private String showHost;

    @Column(name = "\"ShowStartTime\"", nullable = false)
    private LocalDateTime showStartTime;

    @Column(name = "\"ShowEndTime\"", nullable = false)
    private LocalDateTime showEndTime;
}