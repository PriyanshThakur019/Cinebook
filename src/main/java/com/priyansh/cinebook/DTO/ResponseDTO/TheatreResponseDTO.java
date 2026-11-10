package com.priyansh.cinebook.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreResponseDTO {

    private Long theatreDetailId;
    private String theatreOwner;
    private String theatreName;
    private String theatreAddress;
    private String city;
    private String state;
    private String pincode;
    private String contactNumber;
    private String contactEmail;
    private Boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

