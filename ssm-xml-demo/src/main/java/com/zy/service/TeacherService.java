package com.zy.service;

import com.zy.entity.Teacher;

import java.util.List;

public interface TeacherService {
    public void save(Teacher teacher);
    public void deleteById(Integer id);
    public void updateOne(Teacher teacher);
    public Teacher findById(Integer id);
    public List<Teacher> findAll();
    public int findCount();
}
