package com.example.ar_ecommerce;

public class ARProductData {
    private int id;
    private float min;
    private float max;

    public ARProductData(int id, float min, float max) {
        this.id = id;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
}
