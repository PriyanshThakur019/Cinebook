package com.priyansh.cinebook.DTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShowDetailsDTO {
    @NonNull
    private String showName;
    @NonNull
    private Integer showDuration;
    @NonNull
    private String showHost;
    @NonNull
    private LocalDateTime showStartTime;

    @NonNull
    private LocalDateTime showEndTime;

    private String posterURL;
}
