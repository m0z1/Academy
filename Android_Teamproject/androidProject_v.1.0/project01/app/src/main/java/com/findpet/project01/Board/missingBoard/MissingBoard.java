package com.findpet.project01.Board.missingBoard;


import com.findpet.project01.Board.ImgFile;
import com.findpet.project01.account.Member;
import com.findpet.project01.comment.Comment;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MissingBoard implements Serializable {

    @Expose
    private Long missingId;
    private String breed;
    private String content;
    @Expose
    private Date regdate;
    private String petname;
    private String petage;
    private String petgender;
    private String petcharacter;
    private String petcategory;
    private String missingaddr;
    private Member member;
    private List<ImgFile> imgFileList;
    private List<Comment> comment;

    public MissingBoard(Long missingId, String breed, String content, String petname, String petage, String petgender, String petcharacter, String missingaddr) {
        this.missingId = missingId;
        this.breed = breed;
        this.content = content;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.petcharacter = petcharacter;
        this.missingaddr = missingaddr;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public Long getMissingId() {
        return missingId;
    }

    public void setMissingId(Long missingId) {
        this.missingId = missingId;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public List<ImgFile> getImgFileList() {
        return imgFileList;
    }

    public void setImgFileList(List<ImgFile> imgFileList) {
        this.imgFileList = imgFileList;
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

    public String getMissingaddr() {
        return missingaddr;
    }

    public void setMissingaddr(String missingaddr) {
        this.missingaddr = missingaddr;
    }
}
