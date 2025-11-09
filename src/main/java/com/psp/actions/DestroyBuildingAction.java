package com.psp.actions;

import com.psp.cityEntities.BuildingType;
import com.psp.cityEntities.City;

public class DestroyBuildingAction implements Action{
    City city;
    BuildingType buildingType;
    boolean buildingToDestroyWasActive;

    public DestroyBuildingAction(City city, BuildingType buildingType) {
        this.city = city;
        this.buildingType = buildingType;

        if(city.getBuildingManager().getInactiveBuildingCount(buildingType) > 0){
            buildingToDestroyWasActive = false;
        }
        else {
            buildingToDestroyWasActive = true;
        }

    }

    @Override
    public void execute() {
        if(validate()==true) {
            if (buildingToDestroyWasActive) {
                city.getBuildingManager().removeBuilding(buildingType, true);
            } else {
                city.getBuildingManager().removeBuilding(buildingType, false);
            }
        }

    }

    @Override
    public boolean validate() {
        if(city.getBuildingManager().getActiveBuildingCount(buildingType) <= 0 && city.getBuildingManager().getInactiveBuildingCount(buildingType) <= 0){
            System.out.println("No active or inactive " + buildingType.name() + " building to destroy.");
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        if(buildingToDestroyWasActive){
            city.getBuildingManager().addBuilding(buildingType, true);
        }
        else {
            city.getBuildingManager().addBuilding(buildingType, false);
        }
    }
}
