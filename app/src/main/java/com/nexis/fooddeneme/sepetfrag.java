package com.nexis.fooddeneme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sepetfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sepetfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sepetfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sepetfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static sepetfrag newInstance(String param1, String param2) {
        sepetfrag fragment = new sepetfrag();
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

    ImageView degres;
    Button topla, cikar, onayla, bosalt;
    TextView degistext, degisfiyat;

    private static final String ARG_RESIM_ADI = "resimAdi";
    private static final String ARG_YEMEK_ADI = "yemekAdi";
    private static final String ARG_FIYAT = "fiyat";

    private String resimAdi;
    private String yemekAdi;
    private double fiyat;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sepetfrag, container, false);
        topla = view.findViewById(R.id.btntopla);
        cikar = view.findViewById(R.id.btncikar);
        onayla = view.findViewById(R.id.btnonayla);
        bosalt = view.findViewById(R.id.btnbosalt);
        degres = view.findViewById(R.id.imgdegnres);
        degistext = view.findViewById(R.id.txtdegyemek);
        degisfiyat = view.findViewById(R.id.txtdegfiyat);

        Bundle args = getArguments();
        if (args != null) {
            String resimAdi = args.getString(ARG_RESIM_ADI);
            String yemekAdi = args.getString(ARG_YEMEK_ADI);
            double fiyat = args.getDouble(ARG_FIYAT);

            // Görünümlere verileri yerleştirme
            degres.setImageResource(getResources().getIdentifier(resimAdi, "drawable", getContext().getPackageName()));
            degistext.setText(yemekAdi);
            degisfiyat.setText(String.valueOf(fiyat));

        }
        return view;
    }


    public static sepetfrag newInstance(String resimAdi, String yemekAdi, double fiyat) {
        sepetfrag fragment = new sepetfrag();
        Bundle args = new Bundle();
        args.putString(ARG_RESIM_ADI, resimAdi);
        args.putString(ARG_YEMEK_ADI, yemekAdi);
        args.putDouble(ARG_FIYAT, fiyat);
        fragment.setArguments(args);
        return fragment;
    }

}