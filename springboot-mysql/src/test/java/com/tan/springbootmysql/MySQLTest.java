package com.tan.springbootmysql;

import com.tan.springbootmysql.entity.Department;
import com.tan.springbootmysql.entity.Role;
import com.tan.springbootmysql.entity.User;
import com.tan.springbootmysql.repository.DepartmentRepository;
import com.tan.springbootmysql.repository.RoleRepository;
import com.tan.springbootmysql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MySQLTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreateDate(new Date());
        user.setDeparment(department);
        List<Role> roleList = roleRepository.findAll();
        Assert.assertNotNull(roleList);
        user.setRoleList(roleList);
        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);
        for (User user : page.getContent()) {
            log.info("User user name={},department name={},role={}", user.getName(), user.getDeparment().getName(), user.getRoleList());
        }
    }
}
