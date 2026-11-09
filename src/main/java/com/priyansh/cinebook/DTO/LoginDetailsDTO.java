package com.priyansh.cinebook.DTO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDetailsDTO {
    private String username;
    private String password;
}
