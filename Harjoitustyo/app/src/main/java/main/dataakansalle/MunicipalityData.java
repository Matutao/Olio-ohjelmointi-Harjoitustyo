package main.dataakansalle;

import java.io.Serializable;
import java.util.Map;
import org.json.JSONObject;

public class MunicipalityData implements Serializable {
    private String municipalityName;
    private String municipalityID;
    private Map<Integer, Integer> populationData;
    private Map<Integer, Integer> populationChangeData;
    private Map<Integer, Double> employmentData;
    private Map<Integer, Double> workplaceSelfSuffiencyData;
    private Map<Integer, Integer> electricVehicleData;
    private String weatherDataJson; // Store as String for Serialization

    public MunicipalityData() {}

    public String getMunicipalityName() { return municipalityName; }
    public void setMunicipalityName(String municipalityName) { this.municipalityName = municipalityName; }

    public String getMunicipalityID() { return municipalityID; }
    public void setMunicipalityID(String municipalityID) { this.municipalityID = municipalityID; }

    public Map<Integer, Integer> getPopulationData() { return populationData; }
    public void setPopulationData(Map<Integer, Integer> populationData) { this.populationData = populationData; }

    public Map<Integer, Integer> getPopulationChangeData() { return populationChangeData; }
    public void setPopulationChangeData(Map<Integer, Integer> populationChangeData) { this.populationChangeData = populationChangeData; }

    public Map<Integer, Double> getEmploymentData() { return employmentData; }
    public void setEmploymentData(Map<Integer, Double> employmentData) { this.employmentData = employmentData; }

    public Map<Integer, Double> getWorkplaceSelfSuffiencyData() { return workplaceSelfSuffiencyData; }
    public void setWorkplaceSelfSuffiencyData(Map<Integer, Double> workplaceSelfSuffiencyData) { this.workplaceSelfSuffiencyData = workplaceSelfSuffiencyData; }

    public Map<Integer, Integer> getElectricVehicleData() { return electricVehicleData; }
    public void setElectricVehicleData(Map<Integer, Integer> electricVehicleData) { this.electricVehicleData = electricVehicleData; }

    public String getWeatherDataJson() { return weatherDataJson; }
    public void setWeatherDataJson(String weatherDataJson) { this.weatherDataJson = weatherDataJson; }
}
