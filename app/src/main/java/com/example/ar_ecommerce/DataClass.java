package com.example.ar_ecommerce;

public class DataClass {
    private String dataTitle;
    private String dataDescription;
    private String dataCost;
    private String dataImage;
    private String key;

    public DataClass(String dataTitle, String dataDescription, String dataCost, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
        this.dataCost = dataCost;
        this.dataImage = dataImage;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public String getDataCost() {
        return dataCost;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(){
    }
}
