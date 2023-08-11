package com.example.myapp14;

public class Phone {
    private Long id;
    private String name;

    public Phone( String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
