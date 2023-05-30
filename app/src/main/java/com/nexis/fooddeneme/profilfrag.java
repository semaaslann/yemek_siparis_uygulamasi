package com.nexis.fooddeneme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profilfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profilfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profilfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profilfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static profilfrag newInstance(String param1, String param2) {
        profilfrag fragment = new profilfrag();
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

    TextView profilmail, profilperson ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profilfrag, container, false);
        profilmail=view.findViewById(R.id.txtprofilmail);
        profilperson = view.findViewById(R.id.txtprofilperson);


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
                                String email = document.getString("email");

                                // Profil verilerini TextView'lere yerleştirin
                                profilperson.setText(adSoyad);
                                profilmail.setText(email);
                            }
                        }
                    });
        }



        /*if (currentUser != null) {
            String adSoyad = currentUser.getDisplayName();
            String email = currentUser.getEmail();

            // Profil verilerini TextView'lere yerleştirin
            profilperson.setText(adSoyad);
            profilmail.setText(email);
        }*/



        return view;
    }
}