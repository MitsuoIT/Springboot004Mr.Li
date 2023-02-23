package com.example.springboottest002.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboottest002.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;


//我：泛型<StudentEntity>的价值是让mtbatis-plus知道去操作哪个实体类所对应的数据库嘛？？？

//【快捷！】导入<StudentEntity>类名后，已走完了【mapper层】
//必须写泛型————泛型中的内容与entity实体类的类名相同
//如果有多个tables 那就有多个entitys 那就有多个mappers 它们三者一一对应（参见homework2的结构！）
@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {
}

