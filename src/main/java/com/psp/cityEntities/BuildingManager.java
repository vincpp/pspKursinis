package com.psp.cityEntities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class BuildingManager {
    private int landSlots = 20;
    private int residentialBuildings = 5;
    private int commercialBuildings = 1;
    private int industrialBuildings = 1;
    private int publicServiceBuildings = 1;

    private int inactiveResidentialBuildings = 0;
    private int inactiveCommercialBuildings = 0;
    private int inactiveIndustrialBuildings = 0;
    private int inactivePublicServiceBuildings = 0;


    public int getBuildingPrice(BuildingType type) {
        switch (type) {
            case RESIDENTIAL -> {return 5000;}
            case COMMERCIAL -> {return 50000;}
            case INDUSTRIAL -> {return 20000;}
            case PUBLIC_SERVICE -> {return 35000;}
            default -> {
                System.out.println("Unknown building type.");
                return 0;
            }
        }
    }
    public int getInactiveBuildingCount(BuildingType type) {
        return switch (type) {
            case RESIDENTIAL -> inactiveResidentialBuildings;
            case COMMERCIAL -> inactiveCommercialBuildings;
            case INDUSTRIAL -> inactiveIndustrialBuildings;
            case PUBLIC_SERVICE -> inactivePublicServiceBuildings;
        };
    }

    public int getActiveBuildingCount(BuildingType buildingType) {
        return switch (buildingType) {
            case RESIDENTIAL -> residentialBuildings;
            case COMMERCIAL -> commercialBuildings;
            case INDUSTRIAL -> industrialBuildings;
            case PUBLIC_SERVICE -> publicServiceBuildings;
        };
    }
    private int getUsedLandSlots() {
        return residentialBuildings+ inactiveResidentialBuildings + commercialBuildings + inactiveCommercialBuildings + industrialBuildings + inactiveIndustrialBuildings + inactivePublicServiceBuildings + publicServiceBuildings;
    }


    public void addBuilding(BuildingType type, boolean isActive) {

        if (getUsedLandSlots() + 1 > landSlots) {
            System.out.println("Not enough land slots to add a " + type.name() + " building.");
            return;
        }

        if(isActive){
            switch (type) {
                case RESIDENTIAL -> residentialBuildings++;
                case COMMERCIAL -> commercialBuildings++;
                case INDUSTRIAL -> industrialBuildings++;
                case PUBLIC_SERVICE -> publicServiceBuildings++;
                default -> System.out.println("Unknown building type.");
            }
        }
        else {
            switch (type) {
                case RESIDENTIAL -> inactiveResidentialBuildings++;
                case COMMERCIAL -> inactiveCommercialBuildings++;
                case INDUSTRIAL -> inactiveIndustrialBuildings++;
                case PUBLIC_SERVICE -> inactivePublicServiceBuildings++;
                default -> System.out.println("Unknown building type.");
            }
        }
    }

    public void removeBuilding(BuildingType type, boolean isActive) {
        int count = isActive ? getActiveBuildingCount(type) : getInactiveBuildingCount(type);
        if (count > 0) {
            if (isActive) {
                decrementActiveBuilding(type);
            } else {
                decrementInactiveBuilding(type);
            }
        } else {
            System.out.println("No " + (isActive ? "active" : "inactive") + " " + type.name().toLowerCase() + " buildings to remove.");
        }
    }
    private void incrementActiveBuilding(BuildingType type) {
        switch (type) {
            case RESIDENTIAL -> residentialBuildings++;
            case COMMERCIAL -> commercialBuildings++;
            case INDUSTRIAL -> industrialBuildings++;
            case PUBLIC_SERVICE -> publicServiceBuildings++;
        }
    }
    private void decrementActiveBuilding(BuildingType type) {
        switch (type) {
            case RESIDENTIAL -> residentialBuildings--;
            case COMMERCIAL -> commercialBuildings--;
            case INDUSTRIAL -> industrialBuildings--;
            case PUBLIC_SERVICE -> publicServiceBuildings--;
        }
    }
    private void incrementInactiveBuilding(BuildingType type) {
        switch (type) {
            case RESIDENTIAL -> inactiveResidentialBuildings++;
            case COMMERCIAL -> inactiveCommercialBuildings++;
            case INDUSTRIAL -> inactiveIndustrialBuildings++;
            case PUBLIC_SERVICE -> inactivePublicServiceBuildings++;
        }
    }
    private void decrementInactiveBuilding(BuildingType type) {
        switch (type) {
            case RESIDENTIAL -> inactiveResidentialBuildings--;
            case COMMERCIAL -> inactiveCommercialBuildings--;
            case INDUSTRIAL -> inactiveIndustrialBuildings--;
            case PUBLIC_SERVICE -> inactivePublicServiceBuildings--;
        }
    }


    public void deactivateBuilding(BuildingType type) {
        int ActiveCount = getActiveBuildingCount(type);
        if (ActiveCount > 0) {
            incrementInactiveBuilding(type);
            decrementActiveBuilding(type);
        } else {
            System.out.println("No active " + type.name().toLowerCase() + " buildings to deactivate.");
        }
    }

    public void activateBuilding(BuildingType type) {
        int inactiveCount = getInactiveBuildingCount(type);
        if (inactiveCount > 0) {
            decrementInactiveBuilding(type);
            incrementActiveBuilding(type);
        } else {
            System.out.println("No inactive " + type.name().toLowerCase() + " buildings to activate.");
        }
    }



public void increaseLandSlots(int additionalSlots) {
    if (additionalSlots > 0) {
        landSlots += additionalSlots;
    }
}

public void decreaseLandSlots(int slotsToRemove) {
    if (slotsToRemove > 0) {
        int usedSlots = getUsedLandSlots();
        if (landSlots - slotsToRemove >= usedSlots) {
            landSlots -= slotsToRemove;
        } else {
            System.out.println("Cannot decrease land slots below the number of used slots.");
        }
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
