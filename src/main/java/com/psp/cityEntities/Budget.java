package com.psp.cityEntities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Budget {
    private long money=0;
    private long income=0;
    private long policeExpenditure=0;
    private long serviceBuildingUpkeep=0;
    private double taxRate=0;

    public Budget() {
        this.money = 100000;
        this.policeExpenditure = 10000;
        this.taxRate = 10.0;
    }

    public void setServiceBuildingUpkeepByBuildingCount(int serviceBuildingCount){
        this.serviceBuildingUpkeep = (long) serviceBuildingCount * 5000;
    }

    public void refreshIncome(long workingPopulation) {
        this.income = (long) (workingPopulation * 10 * (1.+(this.taxRate / 100.0)));
    }
    public long getExpenditure(){
        return this.policeExpenditure + this.serviceBuildingUpkeep;
    }

    public void increasePoliceExpenditure(long amount){
        policeExpenditure += amount;
    }
    public void decreasePoliceExpenditure(long decreaseBy) {
        if(decreaseBy <= policeExpenditure){
            policeExpenditure -= decreaseBy;
        }
        else{
            System.out.println("Cannot decrease Police expenditure by " + decreaseBy + " as it exceeds current expenditure of " + policeExpenditure);
        }
    }


    public void addMoneyFromIndustrialBuildings(BuildingManager buildingManager){
        int industrialBuildingCount = buildingManager.getIndustrialBuildings();
        int commercialBuildingCount = buildingManager.getCommercialBuildings();
        long addedMoney = buildingManager.getIndustrialBuildings() * 3000;

        addedMoney *=  0.05 * buildingManager.getCommercialBuildings();
        addedMoney += (long) commercialBuildingCount * 500;
        this.money += addedMoney;
    }

    public void increaseTaxRate(int amount){
        if(amount + taxRate > 100){
            System.out.println("Tax rate cannot be increased by " + amount + " as it will exceed 100%");
            return;
        }
        taxRate += (double) amount;
    }
    public void decreaseTaxRate(int amount){
        if(taxRate - amount < 0){
            System.out.println("Tax rate cannot be decreased by " + amount + " as it will go below 0%");
            return;
        }
        taxRate -=  (double)  amount;
    }

    public void decreaseMoney(long amount){
        if(amount > money){
            System.out.println("Cannot decrease money by " + amount + " as it exceeds current budget of " + money);
            return;
        }
        money -= amount;
    }
    public void increaseMoney(long amount){
        money += amount;
    }

    public void updateForNextTurn(Population population, BuildingManager buildingManager) {
        if (population.getPopulationCount() < 0) return;
        this.refreshIncome(population.getWorkingPopulation());
        this.addMoneyFromIndustrialBuildings(buildingManager);
        this.money += this.income - this.getExpenditure();
    }


}
