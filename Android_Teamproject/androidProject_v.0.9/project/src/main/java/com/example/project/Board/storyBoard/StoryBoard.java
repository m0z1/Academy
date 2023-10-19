package com.example.project.Board.storyBoard;

import com.example.project.Board.findBoard.ImgFile;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StoryBoard implements Serializable {

    @Expose
    private Long storyId;
    private String title;
    private String content;

    private List<ImgFile> imgFileList;

    private Date regdate;

    public StoryBoard(Long storyId, String title, String content) {
        this.storyId = storyId;
        this.title = title;
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public List<ImgFile> getImgFileList() {
        return imgFileList;
    }

    public void setImgFileList(List<ImgFile> imgFileList) {
        this.imgFileList = imgFileList;
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
}
