package com.example.cop4655finalprojecthaydenstone;

import java.util.List;

public class SearchData {
    public int total;
    List<YelpBusiness> businesses;
}

class YelpBusiness{
    public String name;
    public String rating;
    public String phone;
    public String is_closed;
    public String image_url;
    Location location;
    Coordinates coordinates;

}

class Location{
    public String address1;
}
class Coordinates {
    public String latitude;
    public String longitude;
}


