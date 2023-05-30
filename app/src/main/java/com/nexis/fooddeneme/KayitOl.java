package com.nexis.fooddeneme;

import static com.nexis.fooddeneme.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Map;

public class KayitOl extends AppCompatActivity {

    EditText adsoyad, sifre, eposta;

    String adisoyadi, posta, sifresi;
    Button kayityap;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_kayit_ol);
        adsoyad =findViewById(R.id.edtadsoyad);
        sifre = findViewById(R.id.edtsifre);
        eposta = findViewById(R.id.edteposta);
        kayityap = findViewById(R.id.btnkayıtolasil);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        kayityap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adisoyadi=adsoyad.getText().toString();
                posta=eposta.getText().toString();
                sifresi=sifre.getText().toString();


                if (!TextUtils.isEmpty(adisoyadi) && !TextUtils.isEmpty(posta) && !TextUtils.isEmpty(sifresi)) {
                    mAuth.createUserWithEmailAndPassword(posta, sifresi)
                            .addOnCompleteListener(KayitOl.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Kullanıcı başarıyla oluşturuldu
                                        String userID = mAuth.getCurrentUser().getUid();

                                        // Firestore'da kullanıcı belgesi oluşturun ve verileri ekleyin
                                        DocumentReference userRef = db.collection("kullanicilar").document(userID);
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("adSoyad", adisoyadi);
                                        user.put("email", posta);

                                        userRef.set(user)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(KayitOl.this, "Kayıt yapıldı", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(KayitOl.this, "Kayıt hatası", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    } else {
                                        // Kayıt başarısız, hata durumunu kontrol edin
                                        Toast.makeText(KayitOl.this, "Kayıt yapılamadı: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(KayitOl.this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT);}


                /*if(!TextUtils.isEmpty(adisoyadi) && !TextUtils.isEmpty(posta) && !TextUtils.isEmpty(sifresi)){
                    mAuth.createUserWithEmailAndPassword(posta,sifresi)
                            .addOnCompleteListener(KayitOl.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(KayitOl.this, "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();

                                }
                            });

                }*/

            }
        });


    }
}