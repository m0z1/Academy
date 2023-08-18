package com.example.team1;

import java.sql.Date;
import java.util.List;

public class StoryBoard {

    private Long storyId;

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public StoryBoard(String title, String content, Date regdate, Member member, List<Image> image, List<Comment> comment) {
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.member = member;
        this.image = image;
        this.comment = comment;
    }

    private String title;
    private String content;

    private Date regdate;

    private Member member;

    private List<Image> image;

    private List<Comment> comment;
}
