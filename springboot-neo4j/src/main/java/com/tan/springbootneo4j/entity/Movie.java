package com.tan.springbootneo4j.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity
public class Movie {
    @Id
    @GeneratedValue
    private Long id;

    String title;

    String year;

    String tagline;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }



    @Relationship(type = "ACTS_IN", direction = Relationship.INCOMING)
    List<Role> roleList = new ArrayList<>();

    public Role addRole(Actor actor, String roleName) {
        Role role = new Role(actor, this, roleName);
        this.roleList.add(role);
        return role;
    }
}
