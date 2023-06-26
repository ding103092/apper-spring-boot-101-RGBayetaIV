package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentX = studentRepository.findStudentByStudentNumber(student.getStudentNumber());

        if (studentX.isPresent()) {
            throw new IllegalStateException("Student already exists!");
        }
        studentRepository.save(student);
    }
}
