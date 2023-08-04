package com.example.myapp07;

import java.io.Serializable;

public class Object implements Serializable {

    private String name;
    private String age;
    private String tel;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }
}

