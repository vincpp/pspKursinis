package com.psp.actions;

import com.psp.cityEntities.BuildingType;
import com.psp.cityEntities.City;

public class NewBuildingAction implements Action {
    City city;
    BuildingType buildingType;

    public NewBuildingAction(City city, BuildingType buildingType) {
        this.city = city;
        this.buildingType = buildingType;
    }
    @Override
    public void execute() {
        if(validate()==true){
            city.getBuildingManager().addBuilding(buildingType,true);
            city.getBudget().decreaseMoney(city.getBuildingManager().getBuildingPrice(buildingType));
        }
    }

    @Override
    public boolean validate() {
        if(city.getBudget().getMoney()< city.getBuildingManager().getBuildingPrice(buildingType)){
            System.out.println("Not enough budget to build a new " + buildingType.name() + " building.");
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBuildingManager().removeBuilding(buildingType, true);
        city.getBudget().increaseMoney(city.getBuildingManager().getBuildingPrice(buildingType));

    }
}
