package com.priyansh.cinebook.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"LoginDetails\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"LoginDetailsID\"")
    private Long loginDetailsId;

    @Column(name = "\"Username\"", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "\"Password\"", nullable = false, length = 255)
    private String password;

    @OneToOne(mappedBy = "loginDetails", fetch = FetchType.LAZY)
    private UserDetails userDetails;
}
