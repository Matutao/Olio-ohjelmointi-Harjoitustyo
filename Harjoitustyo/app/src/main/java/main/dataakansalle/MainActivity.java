package main.dataakansalle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    EditText SearchMunicipality;
    TextView ErrorTextView;
    String MunicipalityInput;
    Button Municipality1;
    Button Municipality2;
    Button Municipality3;
    Button Municipality4;
    Button Municipality5;
    ArrayList<String> LastSearched = new ArrayList<>();

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final String PREFS_NAME = "MunicipalityPrefs";
    private static final String KEY_LAST_SEARCHED = "LastSearched";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        SearchMunicipality = findViewById(R.id.searchMunicipality);
        ErrorTextView = findViewById(R.id.errorTextView);
        Municipality1 = findViewById(R.id.municipality1);
        Municipality2 = findViewById(R.id.municipality2);
        Municipality3 = findViewById(R.id.municipality3);
        Municipality4 = findViewById(R.id.municipality4);
        Municipality5 = findViewById(R.id.municipality5);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadLastSearched();
        updateLastSearchedButtons();
    }

    public void switchToInformation(View view) {
        MunicipalityInput = SearchMunicipality.getText().toString().trim();
        if (MunicipalityInput.isEmpty()) {
            ErrorTextView.setText("Syötä kunnan nimi");
            return;
        }

        MunicipalityChecker checker = new MunicipalityChecker(this);
        String id = checker.getMunicipalityId(MunicipalityInput);

        if (id != null) {
            ErrorTextView.setText("Haetaan tietoja...");
            fetchAndSwitch(id, MunicipalityInput);
        } else {
            ErrorTextView.setText("Kuntaa ei löydy, yritä uudestaan");
        }
    }

    private void fetchAndSwitch(String id, String name) {
        executor.execute(() -> {
            try {
                DataRetriever dr = new DataRetriever();
                DataParser dp = new DataParser();
                MunicipalityData data = new MunicipalityData();
                data.setMunicipalityID(id);
                data.setMunicipalityName(name);

                data.setPopulationData(dp.parsePopulationData(dr.fetchPopulationData(id)));
                data.setPopulationChangeData(dp.parsePopulationChangeData(dr.fetchPopulationData(id)));
                data.setEmploymentData(dp.parseEmploymentData(dr.fetchEmploymentData(id)));
                data.setWorkplaceSelfSuffiencyData(dp.parseWorkplaceSelfSuffiencyData(dr.fetchWorkplaceSelfSuffiencyData(id)));
                data.setElectricVehicleData(dp.parseElectricVehicleData(dr.fetchElectricVehicleData(id)));
                
                org.json.JSONObject weather = dr.fetchWeatherData(name);
                if (weather != null) {
                    data.setWeatherDataJson(weather.toString());
                }

                runOnUiThread(() -> {
                    addToLastSearched(name, "Kunta löytyy");
                    Intent intent = new Intent(MainActivity.this, MunicipalityInfoActivity.class);
                    intent.putExtra("MUNICIPALITY_DATA", data);
                    startActivity(intent);
                    ErrorTextView.setText("");
                    SearchMunicipality.setText("");
                });
            } catch (Exception e) {
                runOnUiThread(() -> ErrorTextView.setText("Virhe haettaessa tietoja"));
            }
        });
    }

    public void switchFromLastSearchedToInformation(View view) {
        Button b = (Button) view;
        String name = b.getText().toString();
        if (!name.isEmpty()) {
            MunicipalityChecker checker = new MunicipalityChecker(this);
            String id = checker.getMunicipalityId(name);
            if (id != null) {
                ErrorTextView.setText("Haetaan tietoja...");
                fetchAndSwitch(id, name);
            }
        }
    }

    public void addToLastSearched(String MunicipalityInput, String result) {
        if (result.equals("Kunta löytyy")) {
            String formattedName = MunicipalityInput.substring(0, 1).toUpperCase() + MunicipalityInput
                    .substring(1).toLowerCase();
            
            LastSearched.remove(formattedName);
            LastSearched.add(0, formattedName);
            
            if (LastSearched.size() > 5) {
                LastSearched.remove(5);
            }
            saveLastSearched();
            updateLastSearchedButtons();
        }
    }

    private void updateLastSearchedButtons() {
        Button[] buttons = {Municipality1, Municipality2, Municipality3, Municipality4, Municipality5};
        for (int i = 0; i < buttons.length; i++) {
            if (i < LastSearched.size()) {
                buttons[i].setText(LastSearched.get(i));
                buttons[i].setVisibility(View.VISIBLE);
            } else {
                buttons[i].setVisibility(View.GONE);
            }
        }
    }

    private void saveLastSearched() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LastSearched.size(); i++) {
            sb.append(LastSearched.get(i));
            if (i < LastSearched.size() - 1) {
                sb.append(",");
            }
        }
        editor.putString(KEY_LAST_SEARCHED, sb.toString());
        editor.apply();
    }

    private void loadLastSearched() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedString = prefs.getString(KEY_LAST_SEARCHED, "");
        if (!savedString.isEmpty()) {
            String[] items = savedString.split(",");
            LastSearched = new ArrayList<>(Arrays.asList(items));
        }
    }
}
