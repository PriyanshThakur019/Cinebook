package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.BookingDetailsDTO;
import com.priyansh.cinebook.DTO.ShowDetailsDTO;
import com.priyansh.cinebook.Entity.BookingDetails;
import com.priyansh.cinebook.Entity.ShowDetails;
import com.priyansh.cinebook.Repository.BookingDetailsRepo;
import com.priyansh.cinebook.Repository.ShowDetailsRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ShowService {
    @Autowired
    private ShowDetailsRepo showDetailsRepo;
    @Autowired
    private BookingDetailsRepo bookingDetailsRepo;

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
                .posterURL(showDetailsDTO.getPosterURL())
                .build();



        showDetailsRepo.save(showDetails);
        return showDetails;
    }

    public BookingDetails bookShow(BookingDetailsDTO bookingDetailsDTO) {
        BookingDetails bookedDetails = null;
        ShowDetails showDetails = showDetailsRepo.findByShowDetailId(bookingDetailsDTO.getShowDetailId()).orElse(null);

        if (ObjectUtils.isEmpty(showDetails)) {
            log.error("show doesn't exist");
            return bookedDetails;
        }

        List<BookingDetails> bookingDetailsList = bookingDetailsRepo.findByUserName(bookingDetailsDTO.getUsername())
                .orElse(new ArrayList<>());

        for (BookingDetails bookingDetails : bookingDetailsList) {
            if (Objects.equals(bookingDetails.getShowDetails().getShowDetailId(), bookingDetailsDTO.getShowDetailId())) {
                bookedDetails = bookingDetails;
                break;
            }
        }

        if (!ObjectUtils.isEmpty(bookedDetails)) {
            log.error("booking already exist");
            return bookedDetails;
        }

        bookedDetails = BookingDetails.builder()
                .showDetails(showDetails)
                .userName(bookingDetailsDTO.getUsername())
                .bookingCost(bookingDetailsDTO.getBookingCost())
                .seatNo(bookingDetailsDTO.getSeatNo())
                .build();

        log.info("booking details information collected");
        log.info("saving booking details");

        bookingDetailsRepo.save(bookedDetails);

        return bookedDetails;
    }

    public List<BookingDetails> findAllBookings() {
        return bookingDetailsRepo.findAll();
    }

    public List<ShowDetails> getAllFutureShowDetails() {
        List<ShowDetails> allShowDetailsList = getAllShowDetails();
        List<ShowDetails> futureShowDetailList = new ArrayList<>();

        for (ShowDetails showDetails : allShowDetailsList) {
            if (showDetails.getShowStartTime().isAfter(LocalDateTime.now())) {
                futureShowDetailList.add(showDetails);
            }
        }

        return futureShowDetailList;
    }
}
