package com.example.cop4655finalprojecthaydenstone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchActivity extends AppCompatActivity {

    private Button SearchButton;
    public EditText usersearchBusiness;
    public EditText usersearchLocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    Intent intent1 = new Intent(SearchActivity.this, MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.navigation_search:
                    break;
                case R.id.navigation_favorites:
                    Intent intent3 = new Intent(SearchActivity.this, FavoritesActivity.class);
                    startActivity(intent3);
                    break;
            }

            return false;
        });


        SearchButton = findViewById(R.id.searchbutton);
        usersearchBusiness = findViewById(R.id.usersearchBusiness);
        usersearchLocation = findViewById(R.id.usersearchLocation);

        SearchButton.setOnClickListener(v -> {
            Intent GetResults = new Intent(SearchActivity.this, ResultsActivity.class);
            GetResults.putExtra("Business", usersearchBusiness.getText().toString());
            GetResults.putExtra("Location", usersearchLocation.getText().toString());
            startActivity(GetResults);
        });
    }
}






