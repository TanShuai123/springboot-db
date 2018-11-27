package com.tan.springbootmongodb;

import com.tan.springbootmongodb.entity.User;
import com.tan.springbootmongodb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongoDBTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        Set<String> roles = new HashSet<>();
        roles.add("manage");
        User user = new User("1","user","12345678","name","email@com.cn",new Date(), roles);
        userRepository.save(user);
    }

    @Test
    public void findAll() {
        List<User> userList = userRepository.findAll();
        Assert.assertNotNull(userList);
        for(User user : userList){
            log.info("===user=== userId:{}, username:{}, password:{}, registrationDate:{}",
                    user.getUserId(), user.getName(), user.getPassword(), user.getRegistrationDate());
        }
    }
}
