package main.dataakansalle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import main.dataakansalle.MunicipalityData;
import main.dataakansalle.R;

public class WeatherFragment extends Fragment {
    private static final String ARG_DATA = "municipality_data";
    private MunicipalityData municipalityData;

    public static WeatherFragment newInstance(MunicipalityData data) {
        WeatherFragment fragment = new WeatherFragment();
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
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        TextView weatherText = view.findViewById(R.id.weatherText);
        ImageView weatherImage = view.findViewById(R.id.weatherImage);

        if (municipalityData != null && municipalityData.getWeatherDataJson() != null) {
            try {
                JSONObject json = new JSONObject(municipalityData.getWeatherDataJson());
                JSONObject main = json.getJSONObject("main");
                
                // Weather data is already in Celsius because we added &units=metric to the API call
                double tempCelsius = main.getDouble("temp");
                
                JSONObject weatherObj = json.getJSONArray("weather").getJSONObject(0);
                String description = weatherObj.getString("description");
                String mainCondition = weatherObj.getString("main").toLowerCase();
                
                double windSpeed = json.getJSONObject("wind").getDouble("speed");

                String weatherInfo = String.format(Locale.getDefault(),
                        "%s\n\nLämpötila: %.1f °C\nSää: %s\nTuuli: %.1f m/s",
                        municipalityData.getMunicipalityName(),
                        tempCelsius,
                        description,
                        windSpeed);
                
                weatherText.setText(weatherInfo);
                
                // Set weather image based on the main condition and wind speed
                setWeatherIcon(weatherImage, mainCondition, windSpeed);

            } catch (JSONException e) {
                weatherText.setText("Säätietoja ei voitu lukea");
                e.printStackTrace();
            }
        } else {
            weatherText.setText("Säätietoja ei saatavilla.");
        }
    }

    private void setWeatherIcon(ImageView imageView, String condition, double windSpeed) {
        int iconRes;
        
        // Use windy icon if it's very windy
        if (windSpeed > 10.0) {
            iconRes = R.drawable.windy;
        } else if (condition.contains("cloud")) {
            iconRes = R.drawable.cloudy;
        } else if (condition.contains("rain") || condition.contains("drizzle") || condition.contains("thunderstorm")) {
            iconRes = R.drawable.rain;
        } else if (condition.contains("clear")) {
            iconRes = R.drawable.sunny;
        } else {
            iconRes = R.drawable.sunny; // Default
        }
        
        imageView.setImageResource(iconRes);
        
        // Remove the color filter so the original colors of your SVGs are shown
        imageView.clearColorFilter();
    }
}
