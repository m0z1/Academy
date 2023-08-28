package com.example.team1;

public class ImgFile {

    private Long imgNum;
    private String imgOriginalName;
    private String imgSaveName;
    private String imgUrl;
    private String mainImg;

    public Long getImgNum() {
        return imgNum;
    }

    public void setImgNum(Long imgNum) {
        this.imgNum = imgNum;
    }

    private FindBoard findBoard;
    private StoryBoard storyBoard;

    public MissyouBoard getMissyouBoard() {
        return missyouBoard;
    }

    public void setMissyouBoard(MissyouBoard missyouBoard) {
        this.missyouBoard = missyouBoard;
    }

    private MissyouBoard missyouBoard;

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


}
