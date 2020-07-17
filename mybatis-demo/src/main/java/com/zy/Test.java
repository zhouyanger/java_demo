package com.zy;

import com.zy.dao.TeacherDao;
import com.zy.entity.Teacher;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder ssFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory ssf = ssFactoryBuilder.build(Test.class.getClassLoader().getResourceAsStream("mybatis.xml"));
        SqlSession sqlSession = ssf.openSession();
        TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
        Teacher teacher = mapper.findById(5);
        System.out.println(teacher);
    }
}
