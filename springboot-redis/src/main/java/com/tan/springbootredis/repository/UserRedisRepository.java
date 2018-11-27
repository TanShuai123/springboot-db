package com.tan.springbootredis.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tan.springbootmysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRedisRepository {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 使用JSON格式的文本作为Redis与Java普通对象互相交换数据的存储格式
     * @param key
     * @param time
     * @param user
     */
    public void add(String key, Long time, User user) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(user), time, TimeUnit.MINUTES);
    }

    public void add(String key, Long time, List<User> userList) {
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(key, gson.toJson(userList), time, TimeUnit.MINUTES);
    }

    public User get(String key) {
        Gson gson = new Gson();
        User user = null;
        String userJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(userJson)) {
            user = gson.fromJson(userJson, User.class);
        }
        return user;
    }

    public List<User> getList(String key) {
        Gson gson = new Gson();
        List<User> userList=null;
        String userListJson = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(userListJson)) {
            userList = gson.fromJson(userListJson, new TypeToken<List<User>>() {
            }.getType());
        }
        return userList;
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }
}
