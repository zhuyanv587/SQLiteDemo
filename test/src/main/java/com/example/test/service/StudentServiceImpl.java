package com.example.test.service;

import android.content.Context;

import com.example.test.dao.StudentDao;
import com.example.test.dao.StudentDaoImpl;
import com.example.test.entity.Student;

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
    public void delete(String studentName) {
        studentDao.delete(studentName);
    }

    @Override
    public void delete(int _id) {
        studentDao.delete(_id);
    }
}
