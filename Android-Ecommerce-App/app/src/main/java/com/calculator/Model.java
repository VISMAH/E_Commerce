package com.calculator;

public class Model {

    private String headline, description, price, brand, type, about, origin;
    private String productImage;

    public Model() {
    }

    public Model(String headline, String description, String price, String brand, String type, String about, String origin, String productImage) {
        this.headline = headline;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.about = about;
        this.origin = origin;
        this.productImage = productImage;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
