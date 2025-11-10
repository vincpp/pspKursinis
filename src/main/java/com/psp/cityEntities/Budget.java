package com.psp.cityEntities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Budget {
    private long money=0;
    private long income=0;
    private long incomeFromTax=0;
    private long incomeFromBuildings=0;
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
        this.income += this.getIncomeFromBuildings();
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


    public void setIncomeFromBuildings(BuildingManager buildingManager){
        int industrialBuildingCount = buildingManager.getIndustrialBuildings();
        int commercialBuildingCount = buildingManager.getCommercialBuildings();
        long addedMoney = (long) buildingManager.getIndustrialBuildings() * 3000;

        addedMoney *=  1.10 * buildingManager.getCommercialBuildings();
        addedMoney += (long) commercialBuildingCount * 500;
        this.incomeFromBuildings = addedMoney;
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

    public void updateForNextTurn(Population population) {
        if (population.getPopulationCount() < 0) return;

        this.refreshIncome(population.getWorkingPopulation());
        this.money += this.income - this.getExpenditure();
    }


}
