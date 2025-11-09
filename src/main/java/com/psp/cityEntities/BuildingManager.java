package com.psp.cityEntities;

public class BuildingManager {
    int residentialBuildings = 0;
    int commercialBuildings = 0;
    int industrialBuildings = 0;
    int publicServiceBuildings = 0;

    int inactiveResidentialBuildings = 0;
    int inactiveCommercialBuildings = 0;
    int inactiveIndustrialBuildings = 0;
    int inactivePublicServiceBuildings = 0;

    public void addBuilding(BuildingType type) {
        switch (type) {
            case RESIDENTIAL:
                residentialBuildings++;
                break;
            case COMMERCIAL:
                commercialBuildings++;
                break;
            case INDUSTRIAL:
                industrialBuildings++;
                break;
            case PUBLIC_SERVICE:
                publicServiceBuildings++;
                break;
        }
    }

    // Copy constructor for deep copying
    public BuildingManager(BuildingManager other) {
        if (other == null) return;
        this.residentialBuildings = other.residentialBuildings;
        this.commercialBuildings = other.commercialBuildings;
        this.industrialBuildings = other.industrialBuildings;
        this.publicServiceBuildings = other.publicServiceBuildings;

        this.inactiveResidentialBuildings = other.inactiveResidentialBuildings;
        this.inactiveCommercialBuildings = other.inactiveCommercialBuildings;
        this.inactiveIndustrialBuildings = other.inactiveIndustrialBuildings;
        this.inactivePublicServiceBuildings = other.inactivePublicServiceBuildings;
    }

    // Default constructor (already present implicitly), keep an explicit one
    public BuildingManager() {}

}
