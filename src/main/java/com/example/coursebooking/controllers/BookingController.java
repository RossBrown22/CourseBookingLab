package com.example.coursebooking.controllers;

import com.example.coursebooking.models.Booking;
import com.example.coursebooking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping(value = "/bookings")
    public ResponseEntity<List<Booking>> getAllBookings(
            @RequestParam(name = "date", required = false) String date
    ){
        if(date != null){
            return new ResponseEntity<>(bookingRepository.findByDate(date), HttpStatus.OK);
        }
        return new ResponseEntity<>(bookingRepository.findAll(), HttpStatus.OK);
    }
}
