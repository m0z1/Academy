package com.example.myapp08;

public class Phone3 {
    private Long id;
    private String name;

    public Phone3(Long id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public Phone3(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    private  String tel;


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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
