package com.example.cop4655finalprojecthaydenstone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity{

    private JsonApi jsonApi;
    private TextView BusinessName;
    private TextView BusinessRating;
    private TextView BusinessLocation;
    private TextView BusinessPhone;
    private TextView BusinessIs_Closed;
    private ImageView BusinessImage;
    private Button nextButton;
    private Button mapButton;
    private Button favoriteButton;
    public static final String API_KEY = "wnPPHg7u5q6VdbtW9Bjwu16REGArwbUrIU1ymmBVBk1me3bjbDHwQkbjowcQY7Ot_-jzVvzKpuPzTVjbcM5_RzmO0iPf7Y0UGmG6ynsHZ6GYpk28keVdX-DIx67PX3Yx";

    private String userSearchBusiness;
    private String userSearchLocation;

    private int businessIndex = 0;
    private SearchData businesses;
    private YelpBusiness activeBusiness;
    DatabaseReference databaseFavorites;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        databaseFavorites = FirebaseDatabase.getInstance().getReference("favorite");

        BusinessName = findViewById(R.id.text_view_name);
        BusinessRating = findViewById(R.id.text_view_rating);
        BusinessLocation = findViewById(R.id.text_view_location);
        BusinessPhone = findViewById(R.id.text_view_phone);
        BusinessIs_Closed = findViewById(R.id.text_view_is_closed);
        nextButton = findViewById(R.id.nextButton);
        mapButton = findViewById(R.id.mapButton);
        favoriteButton = findViewById(R.id.favoriteButton);
        BusinessImage = findViewById(R.id.imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonApi = retrofit.create(JsonApi.class);

        getBusinesses();


        nextButton.setOnClickListener(v -> {
            if (businesses.businesses.size() < 1) {
                Toast.makeText(getApplicationContext(), "No Businesses Loaded", Toast.LENGTH_SHORT).show();
                return;
            }

            businessIndex = businessIndex + 1;
            if (businessIndex > businesses.businesses.size() - 1) businessIndex = 0;

            activeBusiness = businesses.businesses.get(businessIndex);
            displayStore(activeBusiness);
        });

        favoriteButton.setOnClickListener(v -> {
            addFavorite();
        });

        mapButton.setOnClickListener(v -> {
            Intent GetResults = new Intent(ResultsActivity.this, MapsActivity.class);
            GetResults.putExtra("longitude", Double.parseDouble(activeBusiness.coordinates.longitude));
            GetResults.putExtra("latitude", Double.parseDouble(activeBusiness.coordinates.latitude));
            startActivity(GetResults);
        });
    }

    private void displayStore(YelpBusiness business) {
        BusinessName.setText("Name: " + business.name);
        BusinessRating.setText("Rating: " + business.rating + "/5.0");
        BusinessPhone.setText("Phone: " + business.phone);
        BusinessIs_Closed.setText("Status: " + IsClosed(business.is_closed));
        BusinessLocation.setText("Address: " + business.location.address1);
        Picasso.get().load(business.image_url).into(BusinessImage);
    }

    private void addFavorite(){
        activeBusiness = businesses.businesses.get(businessIndex);

        String name = activeBusiness.name;
        String address = activeBusiness.location.address1;
        String phone = activeBusiness.phone;

        String id = databaseFavorites.push().getKey();

        FavoriteDataAdd favorite = new FavoriteDataAdd(id, name, address, phone);

        databaseFavorites.child(id).setValue(favorite);

        Toast.makeText(this, "Favorite added", Toast.LENGTH_LONG).show();
    }


    private String IsClosed(String openclosed){
        if (openclosed == "true"){
            return "Closed";
        }
        else return "Open";
    }

    private void getBusinesses() {
        businessIndex = 0;

        Intent intent = getIntent();

        userSearchBusiness = intent.getStringExtra("Business");
        userSearchLocation = intent.getStringExtra("Location");

        Call<SearchData> call = jsonApi.getBusinesses("Bearer " + API_KEY, userSearchBusiness, userSearchLocation);

        call.enqueue(new Callback<SearchData>() {
            @Override
            public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                Toast.makeText(getApplicationContext(), "Succeeded", Toast.LENGTH_SHORT).show();
                businesses = response.body();
                activeBusiness = businesses.businesses.get(0);
                displayStore(activeBusiness);
            }

            @Override
            public void onFailure(Call<SearchData> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}

