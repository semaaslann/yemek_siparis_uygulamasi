package com.nexis.fooddeneme;

public class Restaurant  {
    int logo;
    String restaurant_adi, yemek_adi;

    public Restaurant(int logo, String restaurant_adi, String yemek_adi) {
        this.logo = logo;
        this.restaurant_adi = restaurant_adi;
        this.yemek_adi = yemek_adi;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getRestaurant_adi() {
        return restaurant_adi;
    }

    public void setRestaurant_adi(String restaurant_adi) {
        this.restaurant_adi = restaurant_adi;
    }

    public String getYemek_adi() {
        return yemek_adi;
    }

    public void setYemek_adi(String yemek_adi) {
        this.yemek_adi = yemek_adi;
    }
}
