package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student xdd = new Student(
                    "Student A",
                    11811270L,
                    LocalDate.of(2000, Month.JANUARY,10)
            );

            Student xpp = new Student(
                    "Student B",
                    11911259L,
                    LocalDate.of(2000, Month.DECEMBER, 25)
            );

            studentRepository.saveAll(List.of(xdd,xpp));
        };
    }
}
