package com.nexis.fooddeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Button kayıtol, girisyap;

    String adisoyadi, posta, sifresi;

    FirebaseAuth mAuth;

    EditText mail , sifre;

    TextView marka, slogan;

    ImageView hamburgerresmi;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kayıtol = findViewById(R.id.btnkayıtol);
        girisyap = findViewById(R.id.btngirisyap);
        mail = findViewById(R.id.edittxtmail);
        sifre = findViewById(R.id.edittxtsifre);
        marka = findViewById(R.id.txtmarka);
        slogan = findViewById(R.id.txtslogan);
        hamburgerresmi= findViewById(R.id.imageham);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posta = mail.getText().toString();
                sifresi = sifre.getText().toString();


                if (!TextUtils.isEmpty(posta) && !TextUtils.isEmpty(sifresi)) {
                    mAuth.signInWithEmailAndPassword(posta, sifresi)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Giriş başarılı, kullanıcının Firestore'daki verilerini kontrol edin
                                        String userID = mAuth.getCurrentUser().getUid();
                                        DocumentReference userRef = db.collection("kullanicilar").document(userID);
                                        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()) {
                                                        // Belge mevcut, verilere erişin
                                                        String adSoyad = document.getString("adSoyad");
                                                        String email = document.getString("email");

                                                        // Diğer işlemleri burada gerçekleştirin, örneğin kullanıcıyı yönlendirin
                                                        Intent intent = new Intent(MainActivity.this, Homegiris.class);
                                                        startActivity(intent);
                                                    }
                                                    else {
                                                        // Belge mevcut değil
                                                        Toast.makeText(MainActivity.this, "Kullanıcı verileri bulunamadı", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    // Firestore hatası
                                                    Toast.makeText(MainActivity.this, "Firestore hatası: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        // Giriş başarısız, hata durumunu kontrol edin
                                        Exception exception = task.getException();
                                        if (exception instanceof FirebaseAuthInvalidUserException) {
                                            // Kullanıcı bulunamadı
                                            Toast.makeText(MainActivity.this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
                                        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                            // Yanlış şifre
                                            Toast.makeText(MainActivity.this, "Yanlış şifre", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Diğer hata durumları
                                            Toast.makeText(MainActivity.this, "Giriş başarısız", Toast.LENGTH_SHORT).show();
                                        }
                                    }



              /*  if(!TextUtils.isEmpty(posta) && !TextUtils.isEmpty(sifresi)){
                    mAuth.signInWithEmailAndPassword(posta, sifresi)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Giriş başarılı, kullanıcıyı yönlendirin
                                        Intent i = new Intent(MainActivity.this, Homegiris.class);
                                        startActivity(i);
                                    } else {
                                        // Giriş başarısız, hata durumunu kontrol edin
                                        Exception exception = task.getException();
                                        if (exception instanceof FirebaseAuthInvalidUserException) {
                                            // Kullanıcı bulunamadı
                                            Toast.makeText(MainActivity.this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
                                        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                            // Yanlış şifre
                                            Toast.makeText(MainActivity.this, "Yanlış şifre", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Diğer hata durumları
                                            Toast.makeText(MainActivity.this, "Giriş başarısız", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });

                }else {
                    Toast.makeText(MainActivity.this, "Eposta veya Şifre Boş Olamaz", Toast.LENGTH_SHORT).show();
                }*/
                                }
                            });
                }else{
                    Toast.makeText(MainActivity.this, "Alanlar Boş Olamaz", Toast.LENGTH_SHORT).show();
                }
            }
        });


        kayıtol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, KayitOl.class);
                startActivity(intent1);

            }
        });
    }
}