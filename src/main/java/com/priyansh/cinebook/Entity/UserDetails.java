package com.priyansh.cinebook.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"UserDetails\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"userDetailsId\"")
    private Long userDetailsId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "\"LoginDetailsID\"",
                    referencedColumnName = "\"LoginDetailsID\"",
                    nullable = false
            ),
            @JoinColumn(
                    name = "\"Username\"",
                    referencedColumnName = "\"Username\"",
                    nullable = false
            )
    })
    @JsonIgnore
    private LoginDetails loginDetails;

    @Column(name = "\"Name\"", nullable = false, length = 100)
    private String name;

    @Column(name = "\"Mobile\"", nullable = false, unique = true, length = 15)
    private String mobile;

    @Column(name = "\"Age\"")
    private Integer age;

    @CreationTimestamp
    @Column(name = "\"created_date\"", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "\"updated_date\"", nullable = false)
    private LocalDateTime updatedDate;
}

