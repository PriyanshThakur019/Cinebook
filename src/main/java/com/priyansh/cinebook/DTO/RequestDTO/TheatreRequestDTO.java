package com.priyansh.cinebook.DTO.RequestDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreRequestDTO {

    private String theatreOwner;
    private String theatreName;
    private String theatreAddress;
    private String city;
    private String state;
    private String pincode;
    private String contactNumber;
    private String contactEmail;
}
