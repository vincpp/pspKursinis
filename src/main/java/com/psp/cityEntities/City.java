package com.psp.cityEntities;

import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class City {
    Population population;
    BuildingManager buildingManager;
    Budget budget;
    CityAttributes attributes;
    public City() {
        this.population = new Population( );
        this.buildingManager = new BuildingManager();
        this.budget = new Budget();
        this.attributes = new CityAttributes();
    }


    public City(City city){
        // Initialize defaults and deep-copy values from the provided city
        this();
        updateCity(city);
    }
    public long getPopulation(){
        return population.getPopulation();
    }
    public double getPopulationGrowthRate(){
        return population.getGrowthRate();
    }
    public int getHappiness(){
        return attributes.getHappiness();
    }
    public int getPollution() {
        return attributes.getPollution();
    }
    public int getSafety() {
        return attributes.getSafety();
    }
    public long getBudgetMoney(){
        return budget.getMoney();
    }
    public long getBudgetIncome(){
        return budget.getIncome();
    }
    public long getBudgetExpenditure() {
        return budget.getExpenditure();
    }
    public double getTaxRate(){
        return budget.getTaxRate();
    }
    public void updateForNextTurn(){
        population.updateForNextTurn();
        attributes.updateForNextTurn(population, buildingManager, budget);
        budget.updateForNextTurn(population);
        // Budget and BuildingManager could also have update methods if needed
    }

    public void updateCity(City city) {
        if (city == null) return;

        // Population
        if (city.population == null) {
            this.population = null;
        } else {
            if (this.population == null) this.population = new Population();
            this.population.setPopulation(city.population.getPopulation());
            this.population.setGrowthRate(city.population.getGrowthRate());
        }

        // BuildingManager
        if (city.buildingManager == null) {
            this.buildingManager = null;
        } else {
            BuildingManager src = city.buildingManager;
            BuildingManager dst = new BuildingManager();
            dst.residentialBuildings = src.residentialBuildings;
            dst.commercialBuildings = src.commercialBuildings;
            dst.industrialBuildings = src.industrialBuildings;
            dst.publicServiceBuildings = src.publicServiceBuildings;

            dst.inactiveResidentialBuildings = src.inactiveResidentialBuildings;
            dst.inactiveCommercialBuildings = src.inactiveCommercialBuildings;
            dst.inactiveIndustrialBuildings = src.inactiveIndustrialBuildings;
            dst.inactivePublicServiceBuildings = src.inactivePublicServiceBuildings;
            this.buildingManager = dst;
        }

        // Budget
        if (city.budget == null) {
            this.budget = null;
        } else {
            if (this.budget == null) this.budget = new Budget();
            this.budget.setMoney(city.budget.getMoney());
            this.budget.setIncome(city.budget.getIncome());
            this.budget.setExpenditure(city.budget.getExpenditure());
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