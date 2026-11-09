package com.priyansh.cinebook.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetailsDTO {
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private Integer age;
    @NonNull
    private Long number;

    private String password;
}

