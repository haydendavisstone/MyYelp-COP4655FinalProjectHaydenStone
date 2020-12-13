package com.example.cop4655finalprojecthaydenstone;


// data class for adding items to database
public class FavoriteDataAdd {
    String id;
    String name;
    String address;
    String Phone;

    public FavoriteDataAdd(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.Phone = phone;
    }

    public String getId() { return id; }

    public String getFavoriteName() {
        return name;
    }

    public String getFavoriteAddress() {
        return address;
    }

    public String getFavoritePhone() {
        return Phone;
    }

    public void setName(String name) { this.name = name; }

    public void setAddress(String address) { this.address = address; }

    public void setPhone(String phone) { this.Phone = phone; }

    public void setId(String id) { this.id = id; }
}
