package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.ShowDetailsDTO;
import com.priyansh.cinebook.Entity.ShowDetails;
import com.priyansh.cinebook.Repository.ShowDetailsRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ShowService {
    @Autowired
    private ShowDetailsRepo showDetailsRepo;

    public List<ShowDetails> getAllShowDetails() {
        return showDetailsRepo.findAll();
    }

    public ShowDetails registerShow(ShowDetailsDTO showDetailsDTO) {
        Long showId = null;

        ShowDetails existingShowDetail = showDetailsRepo.findFirstByShowName(showDetailsDTO.getShowName()).orElse(null);
        if (ObjectUtils.isEmpty(existingShowDetail)) {
            showId = showDetailsRepo.generateNextShowId();
        } else {
            showId = existingShowDetail.getShowId();
        }
        ShowDetails showDetails = ShowDetails.builder()
                .showDuration(showDetailsDTO.getShowDuration())
                .showStartTime(showDetailsDTO.getShowStartTime())
                .showHost(showDetailsDTO.getShowHost())
                .showName(showDetailsDTO.getShowName())
                .showEndTime(showDetailsDTO.getShowEndTime())
                .showId(showId)
                .build();



        showDetailsRepo.save(showDetails);
        return showDetails;
    }
}
