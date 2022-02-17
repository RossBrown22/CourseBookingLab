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
            @RequestParam(name = "course", required = false) String courseName,
            @RequestParam(name = "townName", required = false) String townName,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "name", required = false) String customerName
    ){
        if(age != null && courseName != null && townName != null){
            return new ResponseEntity<>(customerRepository.findByAgeGreaterThanAndTownAndBookingsCourseAllIgnoreCase(age, townName, courseRepository.findByNameIgnoreCase(courseName)), HttpStatus.OK);
        }
        else if (courseName != null && townName != null){
            return new ResponseEntity<>(customerRepository.findByTownAndBookingsCourseAllIgnoreCase(townName, courseRepository.findByNameIgnoreCase(courseName)), HttpStatus.OK);
        }
        else if (courseName != null){
            return new ResponseEntity<>(customerRepository.findByBookingsCourseAllIgnoreCase(courseRepository.findByNameIgnoreCase(courseName)), HttpStatus.OK);
        }
        else if (customerName != null){
            return new ResponseEntity(customerRepository.findByNameIgnoreCase(customerName), HttpStatus.OK);
        }
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }
}
