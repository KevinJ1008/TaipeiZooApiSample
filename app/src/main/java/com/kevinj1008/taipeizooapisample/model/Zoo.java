package com.kevinj1008.taipeizooapisample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zoo {

    /**
     *   "E_Pic_URL": "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg",
     *   "E_Geo": "MULTIPOINT ((121.5805931 24.9985962))",
     *   "E_Info": "臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導觀賞者更正確地認識本土野生動物。臺灣位處於亞熱帶，雨量充沛、氣候溫暖，擁有各種地形景觀，因而孕育了豐富龐雜的生物資源。",
     *   "E_no": "1",
     *   "E_Category": "戶外區",
     *   "E_Name": "臺灣動物區",
     *   "E_Memo": "",
     *   "_id": 1, ""
     *   "E_URL": "http://www.zoo.gov.tw/introduce/gq.aspx?tid=12""MULTIPOINT ((121.5805931 24.9985962))"
     */

    @SerializedName("E_Pic_URL")
    @Expose
    private String mPicture;
    @SerializedName("E_Geo")
    @Expose
    private String mGeo;
    @SerializedName("E_Info")
    @Expose
    private String mInfo;
    @SerializedName("E_no")
    @Expose
    private String mNo;
    @SerializedName("E_Category")
    @Expose
    private String mCategory;
    @SerializedName("E_Name")
    @Expose
    private String mName;
    @SerializedName("E_Memo")
    @Expose
    private String mMemo;
    @SerializedName("_id")
    @Expose
    private int mId;
    @SerializedName("E_URL")
    @Expose
    private String mURL;

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        this.mPicture = picture;
    }

    public String getGeo() {
        return mGeo;
    }

    public void setGeo(String geo) {
        this.mGeo = geo;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        this.mInfo = info;
    }

    public String getNo() {
        return mNo;
    }

    public void setNo(String no) {
        this.mNo = no;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getMemo() {
        return mMemo;
    }

    public void setMemo(String memo) {
        this.mMemo = memo;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String URL) {
        this.mURL = URL;
    }


}
