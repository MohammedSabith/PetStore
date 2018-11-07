package com.example.iamgroot.petstore;

public class Holder {

    /*int profileImage;*/
    int contentImage,buyBtn;   
    private String name, caption;

    public Holder(String name, int contentImage, String caption,int buyBtn) {
        this.name = name;
/*
        this.profileImage = profileImage;
*/
        this.contentImage = contentImage;
        this.caption = caption;
        this.buyBtn = buyBtn;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getContentImage() {
        return contentImage;
    }

    public void setContentImage(int contentImage) {
        this.contentImage = contentImage;
    }

    public int getBuyBtn() {
        return buyBtn;
    }

    public void setBuyBtn(int buyBtn) {
        this.buyBtn= buyBtn;
    }

    /*public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
