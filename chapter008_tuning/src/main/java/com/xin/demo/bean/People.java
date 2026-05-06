package com.xin.demo.bean;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-05 17:04
 * @github https://github.com/xyf527
 * @copyright
 */

public class People {

    private Integer id;
    private String name;
    private Integer age;
    private String job;
    private String sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void print() {
        System.out.println("我是print本人");
    }

}
