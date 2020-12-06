package com.example.cop4655finalprojecthaydenstone;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;

        import com.google.android.material.bottomnavigation.BottomNavigationView;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.navigation.NavController;
        import androidx.navigation.Navigation;
        import androidx.navigation.ui.AppBarConfiguration;
        import androidx.navigation.ui.NavigationUI;
        import com.google.android.material.navigation.NavigationView;

        import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Intent intent1 = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.navigation_search:
                        Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.navigation_favorites:
                        Intent intent3 = new Intent(MainActivity.this, FavoritesActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
