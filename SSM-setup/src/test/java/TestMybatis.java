import cn.kgc.entity.Person;
import cn.kgc.mapper.PersonMapper;
import cn.kgc.util.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestMybatis {
    @Test
    public void testHelloWorld() throws IOException {
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //5.调用接口的方法
        Integer count = sqlSession.getMapper(PersonMapper.class).findCount();
        //6.输出对应的返回值count
        System.out.println("count:" + count);
        //7.关闭 SqlSession
        sqlSession.close();
    }

    @Test
    public void testInsert() throws Exception {
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //5.调用接口的方法
        Person p = new Person();
        p.setName("李四");
        p.setNickname("一个人的夜晚上");
        p.setAge(22);
        sqlSession.getMapper(PersonMapper.class).addPerson(p);
        //6.提交实物，该行代码必须放在关闭sqlsession之前
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testupdate() throws Exception {
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        Person p2 = new Person();
        p2.setId(2);
        p2.setName("赵四");
        p2.setNickname("来啊，造作啊");
        p2.setAge(23);
        sqlSession.getMapper(PersonMapper.class).updatePerson(p2);
        //6.提交实物，该行代码必须放在关闭sqlsession之前
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testdel() throws  Exception{
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        sqlSession.getMapper(PersonMapper.class).delById(2);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testdel2 () throws Exception{
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        Person person = new Person();
        person.setId(2);
        sqlSession.getMapper(PersonMapper.class).delById2(person);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testfindById () throws Exception{
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        Person person = sqlSession.getMapper(PersonMapper.class).findById(1);
        System.out.println("id"+ person.getId()+"name"+ person.getName()+"nickname"+ person.getNickname()+"age"+ person.getAge());
        sqlSession.close();
    }
    @Test
    public void testfindAll () throws IOException{
        String str ="mybatis-config.xml";
        InputStream in =Resources.getResourceAsStream(str);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession = factory.openSession();

        List<Person> all = sqlSession.getMapper(PersonMapper.class).findAll();
        for(Person p:all){
            System.out.println(p);
        }
        sqlSession.close();
    }
    @Test
    public void testfindByName () throws IOException{
        String str ="mybatis-config.xml";
        InputStream in =Resources.getResourceAsStream(str);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession = factory.openSession();

        List<Person> list = sqlSession.getMapper(PersonMapper.class).findByName("wqz");
        for(Person p:list){
            System.out.println(p);
        }
        sqlSession.close();
    }
    @Test
    public void testfindByPerson () throws IOException{
        String str ="mybatis-config.xml";
        InputStream in =Resources.getResourceAsStream(str);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        SqlSession sqlSession = factory.openSession();
        Person person = new Person();
        List<Person> list = sqlSession.getMapper(PersonMapper.class).findByPerson(person);
        for(Person p:list){
            System.out.println(p);
        }
        sqlSession.close();
    }
    @Test
    public void findByPerson2 () throws Exception {
        //1.通过流的机制获取主配置文件mybatis-config.xml的主要配置信息
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.实例化SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.调用builder对象的builder（）方法，获取SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //4.调用factory对象的openSession()方法，获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        String name = "王五";
        String nickname = "123";
        List<Person> list = sqlSession.getMapper(PersonMapper.class).findByPerson2(name,nickname);
        for(Person p:list){
            System.out.println("name:"+p.getName()+"nickname"+p.getNickname());
        }
        sqlSession.close();
    }
    @Test
    public void testfindCount(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        Integer count = sqlSession.getMapper(PersonMapper.class).findCount();
        System.out.println(count);
    }

    @Test
    public void findColList(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        List<Person> colList = sqlSession.getMapper(PersonMapper.class).findColList("Id");
        for (Person p:colList){
            System.out.println(p);


        }
    }
}

