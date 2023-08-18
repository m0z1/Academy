package com.example.team1;

public class Member {
    private Long memberId;
    private String username;

    public Member(String username, String password, String name, String tel, String admin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.admin = admin;
    }

    private String password;
    private String name;
    private String tel;
    private String admin;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
