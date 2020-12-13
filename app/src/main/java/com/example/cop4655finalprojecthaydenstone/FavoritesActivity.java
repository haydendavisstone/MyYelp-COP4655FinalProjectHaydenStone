package com.example.cop4655finalprojecthaydenstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;


public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    favoriteAdapter adapter;
    DatabaseReference mbase;


    // Loads favorite items from database in and removes items clicked by user
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Create a instance of the database and get its reference to display the Recycler view linearly
        mbase = FirebaseDatabase.getInstance().getReference("favorite");

        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FavoriteDataGet> options = new FirebaseRecyclerOptions.Builder<FavoriteDataGet>().setQuery(mbase, FavoriteDataGet.class).build();
        adapter = new favoriteAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new favoriteAdapter.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                adapter.getRef(position).removeValue();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Intent intent1 = new Intent(FavoritesActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigation_search:
                        Intent intent2 = new Intent(FavoritesActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_favorites:
                        break;
                }

                return false;
            }
        });
    }

    // Function to tell the app to start getting data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}
