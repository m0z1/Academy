package com.findpet.project01.Board.shelter;


import java.io.Serializable;

public class Shelter implements Serializable {
    //공고번호
    private String noticeNo;

    //접수일자 & 구조일자
    private String happenDt;

    //품종
    private String kindCd;

    //성별(M: 수컷 / F: 암컷 /Q: 미상)
    private String sexCd;

    //발견장소
    private String happenPlace;

    //특징
    private String specialMark;

    //상태(보호중/공고중)
    private String processState;

    //공고시작일
    private String noticeSdt;

    //공고종료일
    private String noticeEdt;

    //특이사항
    private String noticeComment;

    //이미지
    private String Image;

    //털색
    private String colorCd;

    private String age;

    private String weight;

    //중성화 여부
    private String neuterYn;

    //보호소 이름
    private String careNm;

    private String careTel;

    private String careAddr;

    //관할기관
    private String orgNm;

    //담당자
    private String chargeNm;

    private String officetel;

    public String getColorCd() {
        return colorCd;
    }

    public void setColorCd(String colorCd) {
        this.colorCd = colorCd;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNeuterYn() {
        return neuterYn;
    }

    public void setNeuterYn(String neuterYn) {
        this.neuterYn = neuterYn;
    }

    public String getCareNm() {
        return careNm;
    }

    public void setCareNm(String careNm) {
        this.careNm = careNm;
    }

    public String getCareTel() {
        return careTel;
    }

    public void setCareTel(String careTel) {
        this.careTel = careTel;
    }

    public String getCareAddr() {
        return careAddr;
    }

    public void setCareAddr(String careAddr) {
        this.careAddr = careAddr;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getChargeNm() {
        return chargeNm;
    }

    public void setChargeNm(String chargeNm) {
        this.chargeNm = chargeNm;
    }

    public String getOfficetel() {
        return officetel;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getHappenDt() {
        return happenDt;
    }

    public void setHappenDt(String happenDt) {
        this.happenDt = happenDt;
    }

    public String getKindCd() {
        return kindCd;
    }

    public void setKindCd(String kindCd) {
        this.kindCd = kindCd;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public String getHappenPlace() {
        return happenPlace;
    }

    public void setHappenPlace(String happenPlace) {
        this.happenPlace = happenPlace;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public String getNoticeSdt() {
        return noticeSdt;
    }

    public void setNoticeSdt(String noticeSdt) {
        this.noticeSdt = noticeSdt;
    }

    public String getNoticeEdt() {
        return noticeEdt;
    }

    public void setNoticeEdt(String noticeEdt) {
        this.noticeEdt = noticeEdt;
    }

    public String getNoticeComment() {
        return noticeComment;
    }

    public void setNoticeComment(String noticeComment) {
        this.noticeComment = noticeComment;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
