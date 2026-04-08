package main.dataakansalle;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

// A class that checks if the user's input is actually a municipality
public class MunicipalityChecker {

    private Context context;

    public MunicipalityChecker(Context context) {
        this.context = context;
    }

    public String readLog(String MunicipalityInput) {
        try {
            BufferedReader logReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("MunicipalityCodes.csv"))
            );
            String line;
            while ((line = logReader.readLine()) != null) {
                String[] lines = line.split(";");
                String Municipality = lines[1].trim().toUpperCase();

                if (Municipality.equals(MunicipalityInput)) {
                    logReader.close();
                    return "Kunta löytyy";
                }
            }

        } catch (IOException e) {
        }
        return "Kuntaa ei löydy";
    }

}
