package com.example.userproject;

import java.io.Serializable;

public class user implements Serializable {
    String name;
    int age;
    String phone;

    public user(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
    public user(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
