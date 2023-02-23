package com.example.springboottest002.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboottest002.entity.StudentEntity;
import com.example.springboottest002.mapper.StudentMapper;
import com.example.springboottest002.service.StudentService;
import com.example.springboottest002.service.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

//把好朋友(服务类)注入"Test?Student"
@RestController
//此处，RequestMapping括号内为localHost后的【第1个路径名】
@RequestMapping("Student")
public class StudentController {


//    我：此为自动注入（三种注入类型）中的【基于字段注入】，不被推荐。我可以另改为基于【set方法】与【构造器】来注入（把【mapper层接口对象】，作为【service层实现类】的属性）。
//    【建立依赖关系之“关系链”】先把mapper注入service，再把service注入了controller
//    写法1：【控制反转】 自动注入 产生
    @Autowired
    StudentService student;


//    模仿着上方 继续依赖注入一个
    @Autowired
    StudentMapper studentMapper;

////    写法2（繁琐，放弃）：
//    StudentService student  = new StudentServiceImpl();
//    StudentEntity a =new StudentEntity();
//
//    @RequestMapping("insert")
//    public StudentEntity insert(){
//        a.setName("ha");
//        student.insert(a);
//        return a;
//    }

//    在括号内定义路径
//    RequestMapping括号内为localHost后的【第2个路径名】
    @RequestMapping("/insert")
 public void insert(){
        StudentEntity studentEntity = new StudentEntity();
//        无需用set方法给主键赋值
        studentEntity.setName("TomCat");
        studentEntity.setAddress("Shanghai");
        studentEntity.setPhone("1778866");
        studentEntity.setAge(66);
        studentEntity.setSex("男");
//        插入对象（但没有给前端返回插入的结果）
        student.insert(studentEntity);
    }

    @RequestMapping("/retrieve")
    public List<StudentEntity> retrieve() {
//        未加限制条件地（即“null”空值地）读取出数据库Student表中的全部行，放入List<StudentEntity>中。
        List<StudentEntity> studentEntities = studentMapper.selectList(null);
//        针对于前端的Request，向前端返回检索结果（Response响应对象）
        return studentEntities;
    }


//【思路】在控制层的一个servlet，用服务层的对象（基于依赖注入），调用服务层的相应方法（内含全套业务逻辑） ；然后，返回一个Response响应对象给前端
//    此为change方法（修改）
    @RequestMapping("/change")
    public String change() {
        student.change();
        return "修改成功";
    }

    //此为delete方法（删除）
    @RequestMapping("/delete")
    public String delete() {
        student.delete();
        return "删除成功";
    }

}
