package com.example.ar_ecommerce;

public class DataClass {
    private String dataTitle;
    private String dataDescription;
    private int dataCost;
    private String dataImage;

    public DataClass(String dataTitle, String dataDescription, int dataCost, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
        this.dataCost = dataCost;
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDescription() {
        return dataDescription;
    }

    public int getDataCost() {
        return dataCost;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(){
    }
}
