package main.dataakansalle;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataParser {
    
    public Map<Integer, Integer> parsePopulationData(JSONObject PopulationDataJSON) throws JSONException {
        JSONObject IndexObject = PopulationDataJSON
            .getJSONObject("dimension")
            .getJSONObject("Vuosi")
            .getJSONObject("category")
            .getJSONObject("index");
        
        int StartYear = Integer.MAX_VALUE;
        Iterator<String> keys = IndexObject.keys();
        while (keys.hasNext()) {
            String Key = keys.next();
            int Year = Integer.parseInt(Key);
            if (Year < StartYear) StartYear = Year;
        }

        JSONArray Values = PopulationDataJSON.getJSONArray("value");
        Map<Integer, Integer> PopulationMap = new LinkedHashMap<>();

        for (int i = 0; i < Values.length(); i += 2) {
            int year = StartYear + (i / 2);
            int population = Values.getInt(i + 1);
            PopulationMap.put(year, population);
        }

        return PopulationMap;
    }

    public Map<Integer, Integer> parsePopulationChangeData(JSONObject PopulationDataJSON) throws JSONException {
        JSONObject IndexObject = PopulationDataJSON
            .getJSONObject("dimension")
            .getJSONObject("Vuosi")
            .getJSONObject("category")
            .getJSONObject("index");

        int StartYear = Integer.MAX_VALUE;
        Iterator<String> keys = IndexObject.keys();
        while (keys.hasNext()) {
            String Key = keys.next();
            int Year = Integer.parseInt(Key);
            if (Year < StartYear) StartYear = Year;
        }

        JSONArray Values = PopulationDataJSON.getJSONArray("value");
        Map<Integer, Integer> PopulationChangeMap = new LinkedHashMap<>();

        for (int i = 0; i < Values.length(); i += 2) {
            int year = StartYear + (i / 2);
            int change = Values.getInt(i);
            PopulationChangeMap.put(year, change);
        }

        return PopulationChangeMap;
    }
    
    public Map<Integer, Double> parseEmploymentData(JSONObject EmploymentDataJSON) throws JSONException {
        JSONObject IndexObject = EmploymentDataJSON
            .getJSONObject("dimension")
            .getJSONObject("Vuosi")
            .getJSONObject("category")
            .getJSONObject("index");

        int StartYear = Integer.MAX_VALUE;
        Iterator<String> keys = IndexObject.keys();
        while (keys.hasNext()) {
            String Key = keys.next();
            int Year = Integer.parseInt(Key);
            if (Year < StartYear) StartYear = Year;
        }
        JSONArray EmploymentDataValues = EmploymentDataJSON.getJSONArray("value");
        Map<Integer, Double> EmploymentDataMap = new LinkedHashMap<>();
        for (int i = 0; i < EmploymentDataValues.length(); i++) {
            EmploymentDataMap.put(StartYear + i, EmploymentDataValues.getDouble(i));
        }
        return  EmploymentDataMap;

    }

    public Map<Integer, Double> parseWorkplaceSelfSuffiencyData(JSONObject WorkplaceSelfSuffiencyDataJSON) throws JSONException {
        JSONObject IndexObject = WorkplaceSelfSuffiencyDataJSON
            .getJSONObject("dimension")
            .getJSONObject("Vuosi")
            .getJSONObject("category")
            .getJSONObject("index");

        int StartYear = Integer.MAX_VALUE;
        Iterator<String> keys = IndexObject.keys();
        while (keys.hasNext()) {
            String Key = keys.next();
            int Year = Integer.parseInt(Key);
            if (Year < StartYear) StartYear = Year;
        }
        JSONArray WorkplaceSelfSuffiencyDataValues = WorkplaceSelfSuffiencyDataJSON.getJSONArray("value");
        Map<Integer, Double> WorkplaceSelfSuffiencyDataMap = new LinkedHashMap<>();
        for (int i = 0; i < WorkplaceSelfSuffiencyDataValues.length(); i++) {
            WorkplaceSelfSuffiencyDataMap.put(StartYear + i, WorkplaceSelfSuffiencyDataValues.getDouble(i));
        }
        return  WorkplaceSelfSuffiencyDataMap;

    }

    public Map<Integer, Integer> parseElectricVehicleData(JSONObject ElectricVehicleDataJSON) throws JSONException {
        JSONObject IndexObject = ElectricVehicleDataJSON
            .getJSONObject("dimension")
            .getJSONObject("Vuosi")
            .getJSONObject("category")
            .getJSONObject("index");

        int StartYear = Integer.MAX_VALUE;
        Iterator<String> keys = IndexObject.keys();
        while (keys.hasNext()) {
            String Key = keys.next();
            int Year = Integer.parseInt(Key);
            if (Year < StartYear) StartYear = Year;
        }
        JSONArray ElectricVehicleDataValues = ElectricVehicleDataJSON.getJSONArray("value");
        Map<Integer, Integer> ElectricVehicleDataMap = new LinkedHashMap<>();
        for (int i = 0; i < ElectricVehicleDataValues.length(); i++) {
            int ElectricVehicleDataValue = ElectricVehicleDataValues.isNull(i) ? 0 : ElectricVehicleDataValues.getInt(i); 
            ElectricVehicleDataMap.put(StartYear + i, ElectricVehicleDataValue);
        }
        return ElectricVehicleDataMap;
    }
}
