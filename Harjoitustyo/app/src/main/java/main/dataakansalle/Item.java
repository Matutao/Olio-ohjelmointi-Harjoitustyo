package main.dataakansalle;

public class Item {
    private String PopulationDevelopment;
    private String JobSelfSufficiency;
    private String EmploymentRate;

    public Item(String populationDevelopment, String jobSelfSufficiency, String employmentRate) {
        this.PopulationDevelopment = populationDevelopment;
        this.JobSelfSufficiency = jobSelfSufficiency;
        this.EmploymentRate = employmentRate;
    }

    public String getPopulationDevelopment() {
        return PopulationDevelopment;
    }

    public void setPopulationDevelopment(String populationDevelopment) {
        this.PopulationDevelopment = populationDevelopment;
    }

    public String getJobSelfSufficiency() {
        return JobSelfSufficiency;
    }

    public void setJobSelfSufficiency(String jobSelfSufficiency) {
        this.JobSelfSufficiency = jobSelfSufficiency;
    }

    public String getEmploymentRate() {
        return EmploymentRate;
    }

    public void setEmploymentRate(String employmentRate) {
        this.EmploymentRate = employmentRate;
    }
}
