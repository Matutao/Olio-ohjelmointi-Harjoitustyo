package main.dataakansalle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.dataakansalle.Adapter;
import main.dataakansalle.Item;
import main.dataakansalle.MunicipalityData;
import main.dataakansalle.R;

public class PopulationFragment extends Fragment {
    private static final String ARG_DATA = "municipality_data";
    private MunicipalityData municipalityData;

    public static PopulationFragment newInstance(MunicipalityData data) {
        PopulationFragment fragment = new PopulationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            municipalityData = (MunicipalityData) getArguments().getSerializable(ARG_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_population, container, false);
        
        TextView title = view.findViewById(R.id.Municipality);
        if (title != null && municipalityData != null) {
            title.setText(municipalityData.getMunicipalityName());
        }

        RecyclerView recyclerView = view.findViewById(R.id.PopulationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        List<Item> items = new ArrayList<>();
        if (municipalityData != null) {
            Map<Integer, String> popDev = new TreeMap<>();
            Map<Integer, String> selfSuff = new TreeMap<>();
            Map<Integer, String> empRate = new TreeMap<>();

            if (municipalityData.getPopulationData() != null) {
                for (Map.Entry<Integer, Integer> entry : municipalityData.getPopulationData().entrySet()) {
                    popDev.put(entry.getKey(), "Väestö " + entry.getKey() + ": " + entry.getValue());
                }
            }
            if (municipalityData.getWorkplaceSelfSuffiencyData() != null) {
                for (Map.Entry<Integer, Double> entry : municipalityData.getWorkplaceSelfSuffiencyData().entrySet()) {
                    selfSuff.put(entry.getKey(), "Omavaraisuus " + ": " + entry.getValue() + "%");
                }
            }
            if (municipalityData.getEmploymentData() != null) {
                for (Map.Entry<Integer, Double> entry : municipalityData.getEmploymentData().entrySet()) {
                    empRate.put(entry.getKey(), "Työllisyysaste " + ": " + entry.getValue() + "%");
                }
            }

            java.util.Set<Integer> allYears = new java.util.TreeSet<>();
            allYears.addAll(popDev.keySet());
            allYears.addAll(selfSuff.keySet());
            allYears.addAll(empRate.keySet());

            List<Integer> sortedYears = new ArrayList<>(allYears);
            java.util.Collections.reverse(sortedYears);

            for (Integer year : sortedYears) {
                items.add(new Item(
                        popDev.getOrDefault(year, "Väestö " + year + ": -"),
                        selfSuff.getOrDefault(year, "Omavaraisuus " + year + ": -"),
                        empRate.getOrDefault(year, "Työllisyysaste " + year + ": -")
                ));
            }
        }
        
        recyclerView.setAdapter(new Adapter(getContext(), items));
        return view;
    }
}
