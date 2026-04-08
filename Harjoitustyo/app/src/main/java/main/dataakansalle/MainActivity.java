package main.dataakansalle;

import android.content.Intent;
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

    }
    public void switchToInformation(View view) {
        MunicipalityInput = SearchMunicipality.getText().toString().trim().toUpperCase();
        MunicipalityChecker reader = new MunicipalityChecker(this);
        String result = reader.readLog(MunicipalityInput);
        if (result.equals("Kunta löytyy")) {
            addToLastSearched(MunicipalityInput, result);
            Intent intent = new Intent(this, MunicipalityInfoActivity.class);
            startActivity(intent);
        }
        else {
            ErrorTextView.setText("Kuntaa ei löydy, yritä uudestaan");
        }
    }

    public void switchFromLastSearchedToInformation(View view) {
        Intent intent = new Intent(this, MunicipalityInfoActivity.class);
        startActivity(intent);
    }

    public void addToLastSearched(String MunicipalityInput, String result) {
        if (result.equals("Kunta löytyy")) {
            LastSearched.add(0, MunicipalityInput.substring(0, 1).toUpperCase() + MunicipalityInput
                    .substring(1).toLowerCase());
        }
        if (LastSearched.size() > 5) {
            LastSearched.remove(5);
        }
    }


}

