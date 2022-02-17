package com.example.coursebooking;

import com.example.coursebooking.repositories.BookingRepository;
import com.example.coursebooking.repositories.CourseRepository;
import com.example.coursebooking.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CoursebookingApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	CourseRepository courseRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canFindCourseByRating(){
		assertEquals(1, courseRepository.findByRating(4).size());
	}

	@Test
	public void canFindBookingByDate(){
		assertEquals(2, bookingRepository.findByDate("07-11-21").size());
	}
}
