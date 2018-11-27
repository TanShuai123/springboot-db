package com.tan.springbootredis;

import com.tan.springbootmysql.entity.Department;
import com.tan.springbootmysql.entity.Role;
import com.tan.springbootmysql.entity.User;
import com.tan.springbootredis.repository.UserRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Before
    public void setup(){
        Department department = new Department();
        department.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDeparment(department);

        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        user.setRoleList(roleList);

        userRedisRepository.delete(this.getClass().getName()+":userByName:"+user.getName());
        userRedisRepository.add(this.getClass().getName()+":userByName:"+user.getName(), 10L, user);

    }

    @Test
    public void get(){
        User user = userRedisRepository.get(this.getClass().getName() + ":userByName:user");
        log.info("key={}", this.getClass().getName() + ":userByName:user");
        Assert.assertNotNull(user);
        log.info("======user====== name:{}, department name:{}, role name:{}",
                user.getName(), user.getDeparment().getName(), user.getRoleList().get(0).getName());
    }
}
