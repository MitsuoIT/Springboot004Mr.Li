package com.example.springboottest002.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboottest002.entity.StudentEntity;
import com.example.springboottest002.mapper.StudentMapper;
import com.example.springboottest002.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//记住CRUD对应方法的方法体

//    1实现StudentService接口
//    2重写接口里的方法
//    3实现所有接口的业务逻辑
//    4添加注解@Service————自动注入autowired， 让StudentServiceImpl实现类成为我们的好朋友！
@Service
public class StudentServiceImpl implements StudentService {
    //    依赖注入————把【数据层类 XXX·Mapper】给注入！
    //    所有的【mapper的对象】，都是操作数据库的。只需注入一次！
    //    StudentMapper接口的对象，可调用【该接口继承的BaseMapper接口】下所封装的所有8个CRUD方法。
    @Autowired
    StudentMapper student;

//    依次实现service层接口中的所有的CRUD方法，供controller层调用。

    //此为insert方法
    //nb的mybatis框架————让依赖注入后的student可以快速地调用【mybatis-plus中已封装的方法insert等全部的8个方法】，来操控数据库
    @Override
    public boolean insert(StudentEntity T) {
//        如果不接收，就完事了？（实务中，需告知前端是否成功插入数据嘛？）
        int insert = student.insert(T);
        return insert > 1 ? true : false;
    }

    //此为retrieve方法（查询全部）
    @Override
    public List retrieve() {
        List<StudentEntity> studentEntities = student.selectList(null);
        return studentEntities;
    }


    //    【优化方法】把控制层的change()方法的代码转入此处，在控制层change()方法处直接调用服务层的同名change()方法。
//    如何解决报错比较好？ pull方法至StudentService接口？
    //此为change方法（修改）
    @Override
    public void change() {
//        student.updateById();
//        【关键步骤】通过创建[条件构造器]来查询
        QueryWrapper<StudentEntity> studentEntityQueryWrapper = new QueryWrapper<>();
//        为条件构造器传入【实参】
//        select * from student where name = 'Hikki Utada'
        studentEntityQueryWrapper.eq("name", "Hikki Utada");
        StudentEntity studentEntity = student.selectOne(studentEntityQueryWrapper);
//        想修改什么，就写什么setter语句：
        studentEntity.setSex("更成熟的人");
//        【一般不把CRUD都写在控制层——防黑客】靠注入的mapper对象，直接操作了数据库。
        student.updateById(studentEntity);
    }

    //此为delete方法（删除）
    @Override
    public void delete() {
//        【关键步骤】通过创建[条件构造器]来查询
        QueryWrapper<StudentEntity> studentEntityQueryWrapper = new QueryWrapper<>();
//        为条件构造器传入【实参】
//        select * from student where name = 'Hikki Utada'
        studentEntityQueryWrapper.eq("name", "TomCat");
//        【关键】用autowire依赖注入的dao层的对象，调用dao层的方法，来操作数据库！！！
        student.delete(studentEntityQueryWrapper);
    }
}
