package main;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public final class MunicipalityData {
    String MunicipalityName; 
    String MunicipalityID; 
    DataRetriever DR; 
    DataParser DP; 
    Map<Integer, Integer> PopulationData; 
    Map<Integer, Integer> PopulationChangeData; 
    Map<Integer, Double> EmploymentData; 
    Map<Integer, Double> WorkplaceSelfSuffiencyData; 
    Map<Integer, Integer> ElectricVehicleData; 
    JSONObject WeatherData;

    public MunicipalityData(String MunicipalityName, DataRetriever DR, DataParser DP) throws Exception{
        this.MunicipalityID = fetchMunicipalityID(MunicipalityName);
        this.MunicipalityName = MunicipalityName;
        this.DR = DR;
        this.DP = DP;
        PopulationData = DP.parsePopulationData(DR.fetchPopulationData(MunicipalityID));
        PopulationChangeData = DP.parsePopulationChangeData(DR.fetchPopulationData(MunicipalityID)); 
        EmploymentData = DP.parseEmploymentData(DR.fetchEmploymentData(MunicipalityID)); 
        WorkplaceSelfSuffiencyData = DP.parseWorkplaceSelfSuffiencyData(DR.fetchWorkplaceSelfSuffiencyData(MunicipalityID)); 
        ElectricVehicleData = DP.parseElectricVehicleData(DR.fetchElectricVehicleData(MunicipalityID)); 
        WeatherData = DR.fetchWeatherData(MunicipalityName);

    }

    public void setMunicipalityName(String MunicipalityName){this.MunicipalityName = MunicipalityName;}
    public void setMunicipalityID(String MunicipalityID){this.MunicipalityID = MunicipalityID;}
    public void setPopulationData(Map<Integer, Integer> PopulationData){this.PopulationData = PopulationData;}
    public void setPopulationChangeData(Map<Integer, Integer> PopulationChangeData){this.PopulationChangeData = PopulationChangeData;}
    public void setEmploymentData(Map<Integer, Double> EmploymentData){this.EmploymentData = EmploymentData;}
    public void setWorkplaceSelfSuffiencyData(Map<Integer, Double> WorkplaceSelfSuffiencyData){this.WorkplaceSelfSuffiencyData = WorkplaceSelfSuffiencyData;}
    public void setElectricVehicleData(Map<Integer, Integer> ElectricVehicleData){this.ElectricVehicleData = ElectricVehicleData;}
    public void setWeatherData(JSONObject WeatherData){this.WeatherData = WeatherData;}
    public void setDR(DataRetriever DR){this.DR = DR;}
    public void setDP(DataParser DP){this.DP = DP;}

    public String getMunicipalityName(){return this.MunicipalityName;}
    public String getMunicipalityID(){return this.MunicipalityID;}
    public Map<Integer, Integer> getPopulationData(){return this.PopulationData;}
    public Map<Integer, Integer> getPopulationChangeData(){return  this.PopulationChangeData;}
    public Map<Integer, Double> getEmploymentData(){return this.EmploymentData;}
    public Map<Integer, Double> getWorkplaceSelfSuffiencyData(){return this.WorkplaceSelfSuffiencyData;}
    public Map<Integer, Integer> getElectricVehicleData(){return this.ElectricVehicleData;}
    public JSONObject getWeatherData(){return this.WeatherData;}
    
    public String fetchMunicipalityID(String MunicipalityName) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get("api\\src\\main\\java\\main\\MunicipalityCodes.csv"));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[1].trim().equalsIgnoreCase(MunicipalityName.trim())) {
                return parts[0].trim();
            }
        }
        throw new Exception("Municipality not found: " + MunicipalityName); 
    }

    public void updateAllData(){
        setPopulationData(DP.parsePopulationData(DR.fetchPopulationData(MunicipalityID)));
        setPopulationChangeData(DP.parsePopulationChangeData(DR.fetchPopulationData(MunicipalityID)));
        setEmploymentData(DP.parseEmploymentData(DR.fetchEmploymentData(MunicipalityID)));
        setWorkplaceSelfSuffiencyData(DP.parseWorkplaceSelfSuffiencyData(DR.fetchWorkplaceSelfSuffiencyData(MunicipalityID)));
        setElectricVehicleData(DP.parseElectricVehicleData(DR.fetchElectricVehicleData(MunicipalityID)));
        setWeatherData(DR.fetchWeatherData(MunicipalityName));
    }
}
