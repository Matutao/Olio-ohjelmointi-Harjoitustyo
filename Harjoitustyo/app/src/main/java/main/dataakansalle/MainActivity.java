package main.dataakansalle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText SearchMunicipality;
    TextView ErrorTextView;
    String MunicipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        SearchMunicipality = findViewById(R.id.searchMunicipality);
        ErrorTextView = findViewById(R.id.ErrorTextView);
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
            Intent intent = new Intent(this, MunicipalityInfoActivity.class);
            startActivity(intent);
        }
        else {
            ErrorTextView.setText("Kuntaa ei löydy, yritä uudestaan!");
        }


    }
}