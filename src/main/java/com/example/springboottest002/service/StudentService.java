package com.example.springboottest002.service;

import com.example.springboottest002.entity.StudentEntity;
import java.util.List;


// 我：但为什么没有注解呢？（对比之下，另一个接口StudentMapper有接口）

//定义了个服务层接口！
public interface StudentService {
    boolean insert(StudentEntity student01);

//测试

    List retrieve();
    //    如何解决报错比较好？ pull方法至StudentService接口？
    //此为change方法（修改）
    void change();
    //此为delete方法（删除）
    void delete();
}
