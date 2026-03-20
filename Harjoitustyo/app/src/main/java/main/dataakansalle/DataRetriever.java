package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class DataRetriever {
    
    public JSONObject fetchWeatherData(String MunicipalityName){

        HttpClient Client = HttpClient.newHttpClient();
        String RequestStringBody = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=";  
        String RequestString = String.format(RequestStringBody, MunicipalityName); 
        HttpRequest Request = HttpRequest.newBuilder()
            .uri(URI.create(RequestString))
            .header("Accept", "application/json")
            .header("Authorization", "")
            .GET()
            .build(); 
        try {
            HttpResponse<String> Response = Client.send(Request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + Response.statusCode()); 
            JSONObject jo = new JSONObject( Response.body());
            return jo; 
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            return null; 
        }
    }

    public JSONObject fetchTaficomTilastokeskusData(String JSONBody, String URL){
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "")
                .POST(HttpRequest.BodyPublishers.ofString(JSONBody))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            JSONObject jo = new JSONObject(response.body());
            return jo; 
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            return null;  
        }
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