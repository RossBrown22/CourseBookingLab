package com.example.coursebooking.components;

import com.example.coursebooking.models.Booking;
import com.example.coursebooking.models.Course;
import com.example.coursebooking.models.Customer;
import com.example.coursebooking.repositories.BookingRepository;
import com.example.coursebooking.repositories.CourseRepository;
import com.example.coursebooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CustomerRepository customerRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        Course python = new Course("Intro To Python", "Glasgow", 4);
        courseRepository.save(python);

        Course java = new Course("Intro To Java", "Auchtermuchty", 5);
        courseRepository.save(java);

        Customer noah = new Customer("Noah Valuks", "Auchtermuchty", 27);
        customerRepository.save(noah);

        Customer ross = new Customer("Ross Brown", "Paisley", 25);
        customerRepository.save(ross);

        Customer scott = new Customer("Scott Reoch", "Glasgow", 98234758);
        customerRepository.save(scott);

        Booking booking1 = new Booking("07-11-21", noah, python);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking("07-11-21", scott, python);
        bookingRepository.save(booking2);

        Booking booking3 = new Booking("24-01-22", ross, java);
        bookingRepository.save(booking3);
    }
}
