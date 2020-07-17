package com.zy.dao;

import java.util.List;

import com.zy.entity.Teacher;

public interface TeacherDao {
    public void save(Teacher teacher);
    public void deleteById(Integer id);
    public void updateOne(Teacher teacher);
    public Teacher findById(Integer id);
    public List<Teacher> findAll();
    public int findCount();
}
