package main.dataakansalle;

public class Item {
    private final String PopulationDevelopment;
    private final String JobSelfSufficiency;
    private final String EmploymentRate;

    public Item(String populationDevelopment, String jobSelfSufficiency, String employmentRate) {
        this.PopulationDevelopment = populationDevelopment;
        this.JobSelfSufficiency = jobSelfSufficiency;
        this.EmploymentRate = employmentRate;
    }

    public String getPopulationDevelopment() {
        return PopulationDevelopment;
    }

    public String getJobSelfSufficiency() {
        return JobSelfSufficiency;
    }

    public String getEmploymentRate() {
        return EmploymentRate;
    }
}
