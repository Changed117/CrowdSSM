package com.Changed;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import crowd.mapper.AdminMapper;
import crowd.mapper.RoleMapper;
import crowd.service.api.AdminService;
import crowd.service.api.Impl.AdminServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class test {
    @Test
    public void test1(){
        ApplicationContext context
                 = new ClassPathXmlApplicationContext("spring-persist-mybatis.xml");
        AdminMapper adminMapper = (AdminMapper) context.getBean("adminMapper");
        Admin admin = new Admin();
        admin.setUserName("test");
        admin.setEmail("1376758306@qq.com");
        admin.setLoginAcct("1376758306");
        admin.setUserPswd("159357");
        int insert = adminMapper.insert(admin);
        System.out.println("插入了"+insert+"条数据");
    }

    @Test
    public void testlog(){
        Logger logger = LoggerFactory.getLogger(test.class);

        logger.debug("Hello I am DEBUG!!!");
        logger.debug("Hello I am DEBUG!!!");
        logger.debug("Hello I am DEBUG!!!");

        logger.info("INFO!!!");
        logger.info("INFO!!!");
        logger.info("INFO!!!");

        logger.warn("Warn level！！");
        logger.warn("Warn level！！");

        logger.error("Error!!!");
    }

    @Test
    public void testTx(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext("all-Spring-context.xml");
        AdminMapper adminMapper = (AdminMapper)context.getBean("adminMapper");
        for (int i = 1; i < 238; i++){
            adminMapper.insert(new Admin(null, "loginAcct"+i, "userPswd"+i, "userName"+i, "email"+i, null));
        }

    }

    @Test
    public void testRole(){
        ApplicationContext context
                = new ClassPathXmlApplicationContext("all-Spring-context.xml");
        RoleMapper roleMapper = (RoleMapper)context.getBean("roleMapper");
        for (int i = 1; i < 238; i++){
            roleMapper.insert(new Role(null,"name"+i));
        }

    }
}
