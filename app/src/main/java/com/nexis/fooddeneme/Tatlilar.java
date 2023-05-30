package com.nexis.fooddeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tatlilar extends AppCompatActivity {

    Button mercimek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tatlilar);
        mercimek = findViewById(R.id.btnmercimek);

        mercimek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new sepetfrag();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,fragment).commit();

                Toast.makeText(Tatlilar.this, "bastın", Toast.LENGTH_SHORT).show();



            }
        });
    }
}