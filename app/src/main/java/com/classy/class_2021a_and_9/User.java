package com.classy.class_2021a_and_9;

public class User {

    private String uid = "";
    private String name = "";
    private String phone = "";
    private String favoriteSushi = null;

    public User() { }

    public String getUid() {
        return uid;
    }

    public User setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFavoriteSushi() {
        return favoriteSushi;
    }

    public User setFavoriteSushi(String favoriteSushi) {
        this.favoriteSushi = favoriteSushi;
        return this;
    }
}
