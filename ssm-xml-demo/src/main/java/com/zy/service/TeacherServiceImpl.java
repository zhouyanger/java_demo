package com.zy.service;

import com.zy.dao.TeacherDao;
import com.zy.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    //注入TeacherDao
    @Resource
    private TeacherDao teacherDao;
    @Override
    public void save(Teacher teacher) {
        teacherDao.save(teacher);
    }

    @Override
    public void deleteById(Integer id) {
        teacherDao.deleteById(id);
    }

    @Override
    public void updateOne(Teacher teacher) {
        teacherDao.updateOne(teacher);
    }

    @Override
    public Teacher findById(Integer id) {
        return  teacherDao.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public int findCount() {
        int count = teacherDao.findCount();
        System.out.println(count);
        return count;
    }
}
