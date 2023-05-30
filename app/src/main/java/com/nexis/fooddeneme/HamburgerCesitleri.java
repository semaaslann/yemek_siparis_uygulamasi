package com.nexis.fooddeneme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HamburgerCesitleri extends AppCompatActivity {

    Button bashammbir , bashammiki, bashammuc;
    TextView menubir, menuiki, menuuc , fiyatbir, fiyatiki, fiyatuc;
    ImageView hamresbir, hamresiki, hamresuc;

    LinearLayout container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburger_cesitleri);
        bashammbir = findViewById(R.id.btnhammenubir);
        /*bashammiki= findViewById(R.id.btnhammenuiki);
        bashammuc = findViewById(R.id.btnhammenuuc);*/
        menubir=findViewById(R.id.txthammenubir);
        /*menuiki=findViewById(R.id.txthammenuiki);
        menuuc=findViewById(R.id.txthammenuuc);*/
        fiyatbir=findViewById(R.id.txthambirfiyat);
        /*fiyatiki=findViewById(R.id.txthamikifiyat);
        fiyatuc=findViewById(R.id.txthamucfiyat);*/
        hamresbir=findViewById(R.id.imagehammenubir);
        /*hamresiki=findViewById(R.id.imagehammenuiki);
        hamresuc=findViewById(R.id.imagehammenuuc);*/

        container=findViewById(R.id.fragment_container);


        bashammbir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(HamburgerCesitleri.this, "Sepete Eklendi", Toast.LENGTH_SHORT).show();
                String  resimAdi = "hamburger_resim_1.jpg";
                String yemekAdi = "Hamburger Bir";
                double fiyat = 9.99;

                Fragment sepetFragment = sepetfrag.newInstance(resimAdi, yemekAdi, fiyat);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, sepetFragment)
                        .commit();
            }
        });



    }
}