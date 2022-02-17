package com.example.coursebooking.repositories;

import com.example.coursebooking.models.Course;
import com.example.coursebooking.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByRating(int rating);

    Course findByNameIgnoreCase(String name);

    List<Course> findByBookingsCustomerAllIgnoreCase(Customer customer);
}
