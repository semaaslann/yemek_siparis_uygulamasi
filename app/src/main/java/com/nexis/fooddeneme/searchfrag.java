package com.nexis.fooddeneme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link searchfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class searchfrag extends Fragment implements  RestaurantAdaptor.buttonClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public searchfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static searchfrag newInstance(String param1, String param2) {
        searchfrag fragment = new searchfrag();
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

    ArrayList<Class> targetActivities;

    RecyclerView benimrecycle;
    ArrayList<Restaurant> restaurants;
    RestaurantAdaptor myadaptor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searchfrag, container, false);
        benimrecycle = view.findViewById(R.id.recyclmyrecycle);
        restaurants = new ArrayList<>();

        targetActivities = new ArrayList<>();
        targetActivities.add(HamburgerCesitleri.class);
        targetActivities.add(PizzaCesitleri.class);
        targetActivities.add(Corbacesitleri.class);
        targetActivities.add(VeganCesit.class);
        targetActivities.add(Anayemek.class);
        targetActivities.add(Tatlilar.class);

        myadaptor = new RestaurantAdaptor(restaurants, this);
        benimrecycle.setAdapter(myadaptor);
        benimrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        diziolustur();
        //myadaptor.notifyDataSetChanged();
        return view;
    }

    private void diziolustur() {
        restaurants.add(new Restaurant(R.drawable.animhamburger,"Burger King","Sipariş Ver"));
        restaurants.add(new Restaurant(R.drawable.pizzapizza,"Pizza Pizza","Sipariş Ver"));
        restaurants.add(new Restaurant(R.drawable.corbacilogo,"Çorbacım","Sipariş Ver"));
        restaurants.add(new Restaurant(R.drawable.salatarestaurant,"Vegan","Sipariş Ver"));
        restaurants.add(new Restaurant(R.drawable.kebapcilogo,"Restaurant","Sipariş Ver"));
        restaurants.add(new Restaurant(R.drawable.tatlicidukkan,"Tat Waffle","Sipariş Ver"));

    }
    @Override
    public void onButtonClick(int position) {

        if (position >= 0 && position < targetActivities.size()) {
            Class targetActivity = targetActivities.get(position);
            Intent intent = new Intent(getContext(), targetActivity);
            startActivity(intent);
        }

    }
}