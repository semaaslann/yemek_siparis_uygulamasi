package com.nexis.fooddeneme;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the  {@link homefrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefrag.
     */
    // TODO: Rename and change types and number of parameters
    public static homefrag newInstance(String param1, String param2) {
        homefrag fragment = new homefrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView kAdi, slogan ;

    ViewAnimator viewAnimator;
    int currentIndex = 0;
    Handler handler;
    Runnable runnable;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    de.hdodenhof.circleimageview.CircleImageView profil, pizza ,salata, hamburger,icecek ,corba,anayemek;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefrag, container, false);
        kAdi = view.findViewById(R.id.txtkAdii);
        slogan = view.findViewById(R.id.txthomeslogan);
        profil = view.findViewById(R.id.hdodenprofil);
        viewAnimator = view.findViewById(R.id.viewAnimator);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String userID = currentUser.getUid();

            // Firestore'dan kullanıcı belgesini alın
            db.collection("kullanicilar").document(userID).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String adSoyad = document.getString("adSoyad");

                                // Profil verilerini TextView'lere yerleştirin
                                kAdi.setText(adSoyad);
                            }
                        }
                    });
        }



        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentler, new profilfrag()).commit();
            }
        });

        int[] imageResources = {R.drawable.corbalarrr, R.drawable.hamburger, R.drawable.salatacesitleri, R.drawable.pizzalar};
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex++;
                if (currentIndex == imageResources.length) {
                    currentIndex = 0;
                }
                viewAnimator.setDisplayedChild(currentIndex);
                handler.postDelayed(this, 2000); // Geçiş süresini burada ayarlayabilirsiniz (3000 ms = 3 saniye)
            }
        };

        // İlk geçişi başlatın
        handler.postDelayed(runnable, 3000); // Başlangıç bekleme süresini burada ayarlayabilirsiniz (3000 ms = 3 saniye)

        // Geçişleri durdurmak için ViewAnimator üzerine tıklama olayını da ekleyebilirsiniz
        viewAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Aktiviteden çıkış yapılırken zamanlayıcıyı durdurun
        handler.removeCallbacks(runnable);
    }
}

