package com.example.team1;

public class Image {
    private Long imgNum;
    private String imgOriginalName;
    private String imgSaveName;
    private String imgUrl;

    public Long getImgNum() {
        return imgNum;
    }

    public void setImgNum(Long imgNum) {
        this.imgNum = imgNum;
    }

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

    public FindBoard getFindboard() {
        return findboard;
    }

    public void setFindboard(FindBoard findboard) {
        this.findboard = findboard;
    }

    public StoryBoard getStoryboard() {
        return storyboard;
    }

    public void setStoryboard(StoryBoard storyboard) {
        this.storyboard = storyboard;
    }

    public MissingBoard getMissingboard() {
        return missingboard;
    }

    public void setMissingboard(MissingBoard missingboard) {
        this.missingboard = missingboard;
    }

    public Image(String imgOriginalName, String imgSaveName, String imgUrl, String mainImg, FindBoard findboard, StoryBoard storyboard, MissingBoard missingboard) {
        this.imgOriginalName = imgOriginalName;
        this.imgSaveName = imgSaveName;
        this.imgUrl = imgUrl;
        this.mainImg = mainImg;
        this.findboard = findboard;
        this.storyboard = storyboard;
        this.missingboard = missingboard;
    }

    private String mainImg;

    private FindBoard findboard;

    private StoryBoard storyboard;

    private MissingBoard missingboard;

}
