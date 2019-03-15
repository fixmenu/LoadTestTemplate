package com.asimio.api.demo.model;

public class ZemberekResponseModel {
    String text;
    String norm;
    String morp;

    public ZemberekResponseModel(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getMorp() {
        return morp;
    }

    public void setMorp(String morp) {
        this.morp = morp;
    }

    @Override
    public String toString() {
        return "ZemberekResponseModel{" +
                "text='" + text + '\'' +
                ", norm='" + norm + '\'' +
                ", morp='" + morp + '\'' +
                '}';
    }
}
