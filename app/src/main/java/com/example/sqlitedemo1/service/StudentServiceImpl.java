package com.example.sqlitedemo1.service;

import android.content.Context;

import com.example.sqlitedemo1.dao.StudentDao;
import com.example.sqlitedemo1.dao.StudentDaoImpl;
import com.example.sqlitedemo1.entity.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl(Context context) {
        studentDao = new StudentDaoImpl(context);
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(String StudentName) {
        studentDao.delete(StudentName);
    }
}
