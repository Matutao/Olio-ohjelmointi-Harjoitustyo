package main.dataakansalle.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import main.dataakansalle.Item;
import main.dataakansalle.Adapter;
import main.dataakansalle.MainActivity;
import main.dataakansalle.MunicipalityInfoActivity;
import main.dataakansalle.R;

public class PopulationFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_population, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.PopulationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Item> items =  new ArrayList<Item>();
        items.add(new Item("Väestökehitys: ", "Työpaikkaomavaraisuus: ", "Työllisyysaste"));
        recyclerView.setAdapter(new Adapter(getContext(),items));
        return view;
    }

    public void switchToMainActivity(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

}