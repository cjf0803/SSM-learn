package cn.kgc.mapper;

import cn.kgc.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    //定义查询记录方法数
    public Integer findCount();
    //增加
    public Integer addPerson(Person person);
    //修改功能
    public Integer updatePerson(Person person);
    //删除功能
    public Integer delById(Integer Id);
    //删除功能2
    public Integer delById2(Person person);
    //查询对象
    public Person findById(Integer Id);
    //查询集合
    public List<Person> findAll();
    //模糊查询
    public List<Person> findByName(String name);
    //通过用户名和昵称 绝对查询
    public List<Person> findByPerson(Person person);
    //通过用户名和昵称 绝对查询2
    public List<Person> findByPerson2(@Param("name")String name,@Param("nickname")String nickname);
    //测试￥（不安全）符号和#（安全）的作用
    public List<Person> findColList(@Param("colname")String colname);
}

