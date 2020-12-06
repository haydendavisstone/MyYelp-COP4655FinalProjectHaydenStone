package com.example.cop4655finalprojecthaydenstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Intent intent1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigation_search:
                        Intent intent2 = new Intent(ProfileActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_favorites:
                        Intent intent3 = new Intent(ProfileActivity.this, FavoritesActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }


}