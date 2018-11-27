package com.tan.springbootmysql.repository;

import com.tan.springbootmysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameLike(String name);

    User readByName(String name);

    List<User> getByCreateDateLessThan(Date start);
}
