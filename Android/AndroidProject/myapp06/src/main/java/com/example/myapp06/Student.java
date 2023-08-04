package com.example.myapp06;

import java.io.Serializable;

public class Student implements Serializable {
    private int sno;
    private String name;

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    private String major;

}
