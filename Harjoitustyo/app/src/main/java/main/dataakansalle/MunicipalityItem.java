package main.dataakansalle;

// Item class for last searched cities
public class MunicipalityItem {
    private String SearchedMunicipality;

    public MunicipalityItem(String searchedMunicipality){
        this.SearchedMunicipality = searchedMunicipality;
    }

    public String getSearchedMunicipality() {
        return SearchedMunicipality;
    }

    public void setSearchedMunicipality(String searchedMunicipality) {
        this.SearchedMunicipality = searchedMunicipality;
    }
}


