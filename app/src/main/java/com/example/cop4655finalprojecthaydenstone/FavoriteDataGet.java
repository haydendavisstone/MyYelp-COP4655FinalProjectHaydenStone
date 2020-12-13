package com.example.cop4655finalprojecthaydenstone;


// data class getting items from database
public class FavoriteDataGet {
    String favoriteName;
    String favoriteAddress;
    String favoritePhone;

    public FavoriteDataGet() { }

    public String getFavoriteName() {
        return favoriteName;
    }

    public String getFavoriteAddress() {
        return favoriteAddress;
    }

    public String getFavoritePhone() {
        return favoritePhone;
    }

    public void setName(String name) { this.favoriteName = name; }

    public void setAddress(String address) { this.favoriteAddress = address; }

    public void setPhone(String phone) { this.favoritePhone = phone; }

}
