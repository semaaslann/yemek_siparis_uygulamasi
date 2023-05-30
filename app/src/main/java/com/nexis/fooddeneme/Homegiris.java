package com.nexis.fooddeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Homegiris extends AppCompatActivity {

    BottomNavigationView bottomnavi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homegiris);
        bottomnavi= findViewById(R.id.btnnavi);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new homefrag()).commit();
        bottomnavi.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itemmenü:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new homefrag()).commit();
                        break;

                    case R.id.itemara:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new searchfrag()).commit();
                        break;

                    /*case R.id.itemsipariş:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new siparisfrag()).commit();
                        break;*/

                    case R.id.itemsepet:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new sepetfrag()).commit();
                        break;

                    case R.id.itemprofil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentler, new profilfrag()).commit();
                        break;

                }
                return true;
            }
        });
    }
}