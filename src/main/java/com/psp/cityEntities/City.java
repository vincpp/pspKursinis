package com.psp.cityEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class City {
    private Population population;
    private BuildingManager buildingManager;
    private Budget budget;
    private CityAttributes attributes;

    public City() {
        this.population = new Population( );
        this.buildingManager = new BuildingManager();
        this.budget = new Budget();
        this.attributes = new CityAttributes();
        this.refreshIncome();
        this.budget.setServiceBuildingUpkeepByBuildingCount(buildingManager.getPublicServiceBuildings());
    }

    public City(City city){
        // Initialize defaults and deep-copy values from the provided city
        this();
        updateCity(city);
    }

    public void updateForNextTurn(){
        population.updateForNextTurn();
        attributes.updateForNextTurn(population, buildingManager, budget);
        budget.setServiceBuildingUpkeepByBuildingCount(buildingManager.getPublicServiceBuildings());
        budget.setIncomeFromBuildings(buildingManager);
        budget.updateForNextTurn(population);

    }
    public void refreshIncome(){
        budget.refreshIncome(population.getWorkingPopulation());
    }
    public void updateCity(City city) {
        if (city == null) return;

        // Population
        if (city.population == null) {
            this.population = null;
        } else {
            if (this.population == null) this.population = new Population();
            this.population.setPopulationCount(city.population.getPopulationCount());
            this.population.setGrowthRate(city.population.getGrowthRate());
        }

        // BuildingManager
        if (city.buildingManager == null) {
            this.buildingManager = null;
        } else {
            BuildingManager src = city.buildingManager;
            BuildingManager dst = new BuildingManager();

            dst.setResidentialBuildings(src.getResidentialBuildings());
            dst.setCommercialBuildings(src.getCommercialBuildings());
            dst.setIndustrialBuildings(src.getIndustrialBuildings());
            dst.setPublicServiceBuildings(src.getPublicServiceBuildings());

            dst.setInactiveResidentialBuildings(src.getInactiveResidentialBuildings());
            dst.setInactiveCommercialBuildings(src.getInactiveCommercialBuildings());
            dst.setInactiveIndustrialBuildings(src.getInactiveIndustrialBuildings());
            dst.setInactivePublicServiceBuildings(src.getInactivePublicServiceBuildings());
            this.buildingManager = dst;
        }

        // Budget
        if (city.budget == null) {
            this.budget = null;
        } else {
            if (this.budget == null) this.budget = new Budget();
            this.budget.setMoney(city.budget.getMoney());
            this.budget.setIncome(city.budget.getIncome());
            this.budget.setPoliceExpenditure((city.budget.getPoliceExpenditure()));
            this.budget.setServiceBuildingUpkeep(city.budget.getServiceBuildingUpkeep());
            this.budget.setTaxRate(city.budget.getTaxRate());
        }

        // CityAttributes
        if (city.attributes == null) {
            this.attributes = null;
        } else {
            if (this.attributes == null) this.attributes = new CityAttributes();
            this.attributes.setHappiness(city.attributes.getHappiness());
            this.attributes.setPollution(city.attributes.getPollution());
            this.attributes.setSafety(city.attributes.getSafety());
        }
    }


}