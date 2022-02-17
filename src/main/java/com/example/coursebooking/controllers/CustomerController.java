package com.example.coursebooking.controllers;

import com.example.coursebooking.models.Customer;
import com.example.coursebooking.repositories.CourseRepository;
import com.example.coursebooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/customers")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
//
//    @PutMapping(value = "/customers/{id}")
//    @ResponseBody
//    public ResponseEntity<Customer> putCustomer(@PathVariable(value = "id") Long id, @RequestBody Customer customerDetails){
//        Customer customer = customerRepository.getById(id);
//        customer.setName(customerDetails.getName());
//        customer.setTown(customerDetails.getTown());
//        customer.setAge(customerDetails.getAge());
//        final Customer updatedCustomer = customerRepository.save(customer);
//        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
//    }
}
