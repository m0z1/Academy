package com.findpet.project01.account;

public class Member {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String nickname;
    private String admin;


    public Member(String username, String password, String name, String tel, String nickname, String admin) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.nickname = nickname;
        //this.admin = admin;
    }



    public Member() {
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member(String name, String password, String tel, String nickname) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.nickname = nickname;
    }

    public Member(String username1, String pw, String name, String tel, String nickname) {
        this.username = username1;
        this.name = name;
        this.password = pw;
        this.tel = tel;
        this.nickname = nickname;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
