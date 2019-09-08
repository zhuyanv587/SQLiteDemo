package com.example.sqlitedemo1.dao;

import com.example.sqlitedemo1.entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> selectAllStudents();

    void insert(Student student);

    void update(Student student);

    void delete(String studentName);
}
