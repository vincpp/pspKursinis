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
    }


    public City(City city){
        // Initialize defaults and deep-copy values from the provided city
        this();
        updateCity(city);
    }
//    public long getPopulation(){
//        return population.getPopulation();
//    }
//    public double getPopulationGrowthRate(){
//        return population.getGrowthRate();
//    }
//    public int getHappiness(){
//        return attributes.getHappiness();
//    }
//    public int getPollution() {
//        return attributes.getPollution();
//    }
//    public int getSafety() {
//        return attributes.getSafety();
//    }
//    public long getBudgetMoney(){
//        return budget.getMoney();
//    }
//    public long getBudgetIncome(){
//        return budget.getIncome();
//    }
//    public long getBudgetExpenditure() {
//        return budget.getExpenditure();
//    }
//    public void increaseExpenditure(long amount){
//        budget.increaseExpenditure(amount);
//    }
//    public void decreaseExpenditure(int decreaseBy) {
//        budget.decreaseExpenditure(decreaseBy);
//    }
//    public double getTaxRate(){
//        return budget.getTaxRate();
//    }
//    public void increaseTaxRate(int amount){
//        budget.increaseTaxRate(amount);
//    }
//    public void decreaseTaxRate(int amount){
//        budget.decreaseTaxRate(amount);
//    }
//
//    public void decreaseBudgetMoney(long amount){
//        budget.decreaseMoney(amount);
//    }
//    public void increaseBudgetMoney(long amount){
//        budget.increaseMoney(amount);
//    }
//
    public void updateForNextTurn(){
        population.updateForNextTurn();
        attributes.updateForNextTurn(population, buildingManager, budget);
        budget.updateForNextTurn(population);

    }
//
//    public int getResidentialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getResidentialBuildings();
//      }
//
//      public void setResidentialBuildings(int residentialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setResidentialBuildings(residentialBuildings);
//      }
//
//      public int getCommercialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getCommercialBuildings();
//      }
//
//      public void setCommercialBuildings(int commercialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setCommercialBuildings(commercialBuildings);
//      }
//
//      public int getIndustrialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getIndustrialBuildings();
//      }
//
//      public void setIndustrialBuildings(int industrialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setIndustrialBuildings(industrialBuildings);
//      }
//
//      public int getPublicServiceBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getPublicServiceBuildings();
//      }
//
//      public void setPublicServiceBuildings(int publicServiceBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setPublicServiceBuildings(publicServiceBuildings);
//      }
//
//      public int getInactiveResidentialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getInactiveResidentialBuildings();
//      }
//
//      public void setInactiveResidentialBuildings(int inactiveResidentialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setInactiveResidentialBuildings(inactiveResidentialBuildings);
//      }
//
//      public int getInactiveCommercialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getInactiveCommercialBuildings();
//      }
//
//      public void setInactiveCommercialBuildings(int inactiveCommercialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setInactiveCommercialBuildings(inactiveCommercialBuildings);
//      }
//
//      public int getInactiveIndustrialBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getInactiveIndustrialBuildings();
//      }
//
//      public void setInactiveIndustrialBuildings(int inactiveIndustrialBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setInactiveIndustrialBuildings(inactiveIndustrialBuildings);
//      }
//
//      public int getInactivePublicServiceBuildings() {
//          return buildingManager == null ? 0 : buildingManager.getInactivePublicServiceBuildings();
//      }
//
//      public void setInactivePublicServiceBuildings(int inactivePublicServiceBuildings) {
//          if (this.buildingManager == null) this.buildingManager = new BuildingManager();
//          this.buildingManager.setInactivePublicServiceBuildings(inactivePublicServiceBuildings);
//      }
//
//
//      public int getBuildingPrices(BuildingType buildingType) {
//        return buildingManager.getBuildingPrice(buildingType);
//      }

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
//            BuildingManager src = city.buildingManager;
//            BuildingManager dst = new BuildingManager();
//            dst.residentialBuildings = src.residentialBuildings;
//            dst.commercialBuildings = src.commercialBuildings;
//            dst.industrialBuildings = src.industrialBuildings;
//            dst.publicServiceBuildings = src.publicServiceBuildings;
//
//            dst.inactiveResidentialBuildings = src.inactiveResidentialBuildings;
//            dst.inactiveCommercialBuildings = src.inactiveCommercialBuildings;
//            dst.inactiveIndustrialBuildings = src.inactiveIndustrialBuildings;
//            dst.inactivePublicServiceBuildings = src.inactivePublicServiceBuildings;
//            this.buildingManager = dst;
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