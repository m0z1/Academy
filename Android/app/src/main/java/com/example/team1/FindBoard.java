package com.example.team1;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class FindBoard {
    @Expose
    private Long findId;
    private String breed;
    private String content;
    @Expose
    private Date regdate;
    private String petname;
    private String petage;

    private String petgender;
    private String petcharacter;
    private String petcategory;
    private String findaddr;






    public Long getFindId() {
        return findId;
    }

    public void setFindId(Long findId) {
        this.findId = findId;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetage() {
        return petage;
    }

    public void setPetage(String petage) {
        this.petage = petage;
    }

    public String getPetgender() {
        return petgender;
    }

    public void setPetgender(String petgender) {
        this.petgender = petgender;
    }

    public String getPetcharacter() {
        return petcharacter;
    }

    public void setPetcharacter(String petcharacter) {
        this.petcharacter = petcharacter;
    }

    public String getPetcategory() {
        return petcategory;
    }

    public void setPetcategory(String petcategory) {
        this.petcategory = petcategory;
    }

    public String getFindaddr() {
        return findaddr;
    }

    public void setFindaddr(String findaddr) {
        this.findaddr = findaddr;
    }




}
