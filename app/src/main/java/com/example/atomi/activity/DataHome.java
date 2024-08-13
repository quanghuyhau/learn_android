package com.example.atomi.activity;

public class DataHome {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_HEADER = 1;

    private int type;
    private int dataImage;
    private String dataTitle;
    private String dataSubTitle;

    public DataHome(int type, int dataImage, String dataTitle, String dataSubTitle) {
        this.type = type;
        this.dataImage = dataImage;
        this.dataTitle = dataTitle;
        this.dataSubTitle = dataSubTitle;
    }

    public int getType() {
        return type;
    }

    public int getDataImage() {
        return dataImage;
    }

    public void setDataImage(int dataImage) {
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataSubTitle() {
        return dataSubTitle;
    }

    public void setDataSubTitle(String dataSubTitle) {
        this.dataSubTitle = dataSubTitle;
    }
}
