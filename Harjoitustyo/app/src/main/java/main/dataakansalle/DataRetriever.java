package main.dataakansalle;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class DataRetriever {

    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String API_KEY = "ea1d02a6f21a7f3ccd07ba100953d4ee";

    public JSONObject fetchWeatherData(String MunicipalityName){
        try {
            String encodedName = URLEncoder.encode(MunicipalityName, StandardCharsets.UTF_8.toString());
            String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s", encodedName, API_KEY);

            Request request = new Request.Builder()
                    .url(url)
                    .header("Accept", "application/json")
                    .get()
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    return new JSONObject(response.body().string());
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject fetchTaficomTilastokeskusData(String JSONBody, String URL){
        RequestBody body = RequestBody.create(JSONBody, JSON);
        Request request = new Request.Builder()
                .url(URL)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return new JSONObject(response.body().string());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject fetchPopulationData(String MunicipalityID){
        String JSONBody = """
                            {
                            "query": [
                                {
                                "code": "Alue",
                                "selection": {
                                "filter": "item",
                                "values": [
                                "KU%s"
                                ]
                                }
                                },
                                {
                                "code": "Tiedot",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "kokmuutos",
                                    "vaesto"
                                    ]
                                }
                                }
                            ],
                            "response": {
                                "format": "json-stat2"
                            }
                        }
                        """;
        String RequestQuery = String.format(JSONBody, MunicipalityID);
        return fetchTaficomTilastokeskusData(RequestQuery, "https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/synt/statfin_synt_pxt_12dy.px");

    }

    public JSONObject fetchEmploymentData(String MunicipalityID){
        String JSONBody = """
                            {
                            "query": [
                                {
                                "code": "Alue",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "KU%s"
                                    ]
                                }
                                },
                                {
                                "code": "Tiedot",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "tyollisyysaste"
                                    ]
                                }
                                }
                            ],
                            "response": {
                                "format": "json-stat2"
                            }
                            }
                            """;
        String RequestQuery = String.format(JSONBody, MunicipalityID);
        return fetchTaficomTilastokeskusData(RequestQuery, "https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px");

    }

    public JSONObject fetchWorkplaceSelfSuffiencyData(String MunicipalityID){
        String JSONBody = """
                            {
                            "query": [
                                {
                                "code": "Alue",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "KU%s"
                                    ]
                                }
                                }
                            ],
                            "response": {
                                "format": "json-stat2"
                            }
                            }
                            """;
        String RequestQuery = String.format(JSONBody, MunicipalityID);
        return fetchTaficomTilastokeskusData(RequestQuery, "https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_125s.px");

    }

    public JSONObject fetchElectricVehicleData(String MunicipalityID){
        String JSONBody = """
                            {
                            "query": [
                                {
                                "code": "Alue",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "KU%s"
                                    ]
                                }
                                },
                                {
                                "code": "Käyttövoima",
                                "selection": {
                                    "filter": "item",
                                    "values": [
                                    "04"
                                    ]
                                }
                                }
                            ],
                            "response": {
                                "format": "json-stat2"
                            }
                            }
                            """;
        String RequestQuery = String.format(JSONBody, MunicipalityID);
        return fetchTaficomTilastokeskusData(RequestQuery, "https://trafi2.stat.fi/PXWeb/api/v1/fi/TraFi/Ensirekisteroinnit/020_ensirek_tau_102.px");

    }
}
