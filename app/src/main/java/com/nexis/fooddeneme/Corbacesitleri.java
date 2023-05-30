package com.nexis.fooddeneme;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Corbacesitleri extends AppCompatActivity {

    Button mercimek, yayla, tarhana;
    TextView mer, tar, yay, merfiyat, tarfiyat, yayfiyat;
    ImageView merres, tarres,yayres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corbacesitleri);
        mercimek = findViewById(R.id.btnmercimek);
        tarhana = findViewById(R.id.btntarhana);
        yayla = findViewById(R.id.btnyayla);
        mer = findViewById(R.id.txtmercimek);
        tar = findViewById(R.id.txttarhana);
        yay = findViewById(R.id.txtyayla);
        merfiyat = findViewById(R.id.txtmerfiyat);
        tarfiyat = findViewById(R.id.txttarfiyat);
        yayfiyat = findViewById(R.id.txtyaylafiyat);
        merres = findViewById(R.id.imagemercimek);
        tarres = findViewById(R.id.imagetarhana);
        yayres = findViewById(R.id.imageyayla);
        setContentView(R.layout.activity_corbacesitleri);


    }

}