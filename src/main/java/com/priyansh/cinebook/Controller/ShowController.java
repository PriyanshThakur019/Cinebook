package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.BookingDetailsDTO;
import com.priyansh.cinebook.DTO.ShowDetailsDTO;
import com.priyansh.cinebook.Entity.BookingDetails;
import com.priyansh.cinebook.Entity.ShowDetails;
import com.priyansh.cinebook.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping("/findAllShows")
    public List<ShowDetails> findAllShows() {
        return showService.getAllShowDetails();
    }

    @PostMapping("/registerShow")
    public ShowDetails registerShow(@RequestBody ShowDetailsDTO showDetailsDTO) {
        return showService.registerShow(showDetailsDTO);
    }

    @PostMapping("/bookShow")
    public BookingDetails bookShow(@RequestBody BookingDetailsDTO bookingDetailsDTO) {
        return showService.bookShow(bookingDetailsDTO);
    }
}
