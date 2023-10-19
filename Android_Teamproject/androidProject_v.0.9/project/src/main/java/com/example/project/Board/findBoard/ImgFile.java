package com.example.project.Board.findBoard;

import com.example.project.Board.missingBoard.MissingBoard;
import com.example.project.Board.storyBoard.StoryBoard;

public class ImgFile {

    private Long imgNum;
    private String imgOriginalName;
    private String imgSaveName;
    private String imgUrl;
    private String mainImg;
    private FindBoard findBoard;
    private StoryBoard storyBoard;
    private MissingBoard missingBoard;

    public String getImgOriginalName() {
        return imgOriginalName;
    }

    public void setImgOriginalName(String imgOriginalName) {
        this.imgOriginalName = imgOriginalName;
    }

    public String getImgSaveName() {
        return imgSaveName;
    }

    public void setImgSaveName(String imgSaveName) {
        this.imgSaveName = imgSaveName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public FindBoard getFindBoard() {
        return findBoard;
    }

    public void setFindBoard(FindBoard findBoard) {
        this.findBoard = findBoard;
    }

    public StoryBoard getStoryBoard() {
        return storyBoard;
    }

    public void setStoryBoard(StoryBoard storyBoard) {
        this.storyBoard = storyBoard;
    }

    public MissingBoard getMissingBoard() {
        return missingBoard;
    }

    public void setMissingBoard(MissingBoard missingBoard) {
        this.missingBoard = missingBoard;
    }
}
