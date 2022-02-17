package com.example.coursebooking.controllers;

import com.example.coursebooking.models.Customer;
import com.example.coursebooking.repositories.CourseRepository;
import com.example.coursebooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(name = "course", required = false) String name,
            @RequestParam(name = "townName", required = false) String townName,
            @RequestParam(name = "age", required = false) Integer age
    ){
        if(age != null && name != null && townName != null){
            return new ResponseEntity<>(customerRepository.findByAgeGreaterThanAndTownAndBookingsCourse(age, townName, courseRepository.findByName(name)), HttpStatus.OK);
        }
        else if (name != null && townName != null){
            return new ResponseEntity<>(customerRepository.findByTownAndBookingsCourse(townName, courseRepository.findByName(name)), HttpStatus.OK);
        }
        else if (name != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourse(courseRepository.findByName(name)), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }
}
