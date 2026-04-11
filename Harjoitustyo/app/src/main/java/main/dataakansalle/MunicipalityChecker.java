package main.dataakansalle;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.Normalizer;

public class MunicipalityChecker {

    private final Context context;

    public MunicipalityChecker(Context context) {
        this.context = context;
    }

    public String getMunicipalityId(String MunicipalityInput) {
        if (MunicipalityInput == null || MunicipalityInput.trim().isEmpty()) {
            return null;
        }

        String input = MunicipalityInput.trim();
        String[] charsets = {"UTF-8", "ISO-8859-1", "windows-1252"};

        for (String charsetName : charsets) {
            try {
                String id = searchInFile(input, charsetName);
                if (id != null) return id;
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    private String searchInFile(String search, String charsetName) throws IOException {
        String normalizedSearch = Normalizer.normalize(search, Normalizer.Form.NFC);
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open("MunicipalityCodes.csv"), Charset.forName(charsetName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    String nameInFile = Normalizer.normalize(parts[1].trim(), Normalizer.Form.NFC);
                    if (nameInFile.equalsIgnoreCase(normalizedSearch)) {
                        return parts[0].trim();
                    }
                }
            }
        }
        return null;
    }

    public String readLog(String MunicipalityInput) {
        if (getMunicipalityId(MunicipalityInput) != null) {
            return "Kunta löytyy";
        }
        return "Kuntaa ei löydy";
    }
}
