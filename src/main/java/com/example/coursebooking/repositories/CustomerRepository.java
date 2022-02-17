package com.example.coursebooking.repositories;

import com.example.coursebooking.models.Course;
import com.example.coursebooking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByBookingsCourse(Course course);

    Customer findByName(String name);

    List<Customer> findByTownAndBookingsCourse(String townName, Course course);

    List<Customer> findByAgeGreaterThanAndTownAndBookingsCourse(int age, String townName, Course course);
}
