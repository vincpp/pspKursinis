package com.psp.actions;

import com.psp.cityEntities.BuildingType;
import com.psp.cityEntities.City;

public class FixBuildingAction implements Action{
    City city;
    BuildingType buildingType;

    public FixBuildingAction(City city, BuildingType buildingType) {
        this.city = city;
        this.buildingType = buildingType;
    }

    @Override
    public void execute() {
        if (validate() == true) {
            city.getBuildingManager().activateBuilding(buildingType);
            city.getBudget().decreaseMoney(city.getBuildingManager().getBuildingPrice(buildingType) / 2);
        }
    }

    @Override
    public boolean validate() {
        if(city.getBudget().getMoney() < city.getBuildingManager().getBuildingPrice(buildingType)/2){
            System.out.println("Not enough budget to fix a " + buildingType.name() + " building.");
            return false;
        }
        if(city.getBuildingManager().getInactiveBuildingCount(buildingType) <= 0){
            System.out.println("No inactive " + buildingType.name() + " building to fix.");
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBuildingManager().deactivateBuilding(buildingType);
        city.getBudget().increaseMoney(city.getBuildingManager().getBuildingPrice(buildingType) / 2);
    }
}
