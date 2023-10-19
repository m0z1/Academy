package com.findpet.project01.Board.findBoard;
import com.findpet.project01.Board.ImgFile;
import com.findpet.project01.account.Member;
import com.findpet.project01.comment.Comment;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FindBoard implements Serializable {
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
    private Member member;
    private List<ImgFile> imgFileList;
    private List<Comment> comment;

    //수정


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

    public FindBoard(Long findId, String breed, String content, String petname, String petage, String petgender, String petcharacter, String findaddr) {
        this.findId = findId;
        this.breed = breed;
        this.content = content;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.petcharacter = petcharacter;
        this.findaddr = findaddr;
    }



    public List<ImgFile> getImgFileList() {
        return imgFileList;
    }

    public void setImgFileList(List<ImgFile> imgFileList) {
        this.imgFileList = imgFileList;
    }

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
