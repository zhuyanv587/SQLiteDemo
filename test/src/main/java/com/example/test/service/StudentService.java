package com.example.test.service;

import com.example.test.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void insert(Student student);

    void update(Student student);

    void delete(String studentName);

    void delete(int _id);
}
