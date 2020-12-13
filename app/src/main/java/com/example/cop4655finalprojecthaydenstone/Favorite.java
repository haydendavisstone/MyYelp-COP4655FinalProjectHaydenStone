package com.example.cop4655finalprojecthaydenstone;

public class Favorite {
    String id;
    String name;
    String address;
    String Phone;

    public Favorite(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.Phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getFavoriteName() {
        return name;
    }

    public String getFavoriteAddress() {
        return address;
    }

    public String getFavoritePhone() {
        return Phone;
    }
}
