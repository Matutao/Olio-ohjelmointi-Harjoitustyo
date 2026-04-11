package main.dataakansalle.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import main.dataakansalle.MunicipalityData;
import main.dataakansalle.R;

public class VehiclesFragment extends Fragment {
    private static final String ARG_DATA = "municipality_data";
    private MunicipalityData municipalityData;

    public static VehiclesFragment newInstance(MunicipalityData data) {
        VehiclesFragment fragment = new VehiclesFragment();
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
        return inflater.inflate(R.layout.fragment_vehicles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        BarChart barChart = view.findViewById(R.id.barChart);
        TextView title = view.findViewById(R.id.vehicleTitle);

        if (municipalityData != null && municipalityData.getElectricVehicleData() != null) {
            title.setText(municipalityData.getMunicipalityName() + ": Sähköautojen ensirekistöröinnit");

            Map<Integer, Integer> vehicleData = municipalityData.getElectricVehicleData();
            List<Integer> years = new ArrayList<>(vehicleData.keySet());
            Collections.sort(years);

            if (years.isEmpty()) return;

            List<BarEntry> entries = new ArrayList<>();
            for (int i = 0; i < years.size(); i++) {
                Integer year = years.get(i);
                Integer count = vehicleData.get(year);
                entries.add(new BarEntry(i, count != null ? count.floatValue() : 0f));
            }

            BarDataSet dataSet = new BarDataSet(entries, "Ensirekisteröinnit");
            dataSet.setColor(Color.parseColor("#609FA4"));
            dataSet.setValueTextSize(12f);
            dataSet.setValueTextColor(Color.BLACK);

            BarData barData = new BarData(dataSet);
            barChart.setData(barData);

            // Configure X-axis to be hidden
            XAxis xAxis = barChart.getXAxis();
            xAxis.setEnabled(false); // This removes the year labels and the axis line

            // General chart settings
            barChart.getDescription().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.getAxisRight().setEnabled(false);
            barChart.getAxisLeft().setDrawGridLines(false);
            barChart.getAxisLeft().setAxisMinimum(0f);

            barChart.animateY(1000);
            barChart.invalidate(); // Refresh
        }
    }
}
