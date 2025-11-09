package com.psp.cityEntities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Budget {
    private long money=0;
    private long income=0;
    private long expenditure=0;
    private double taxRate=0;

    public Budget(long money, long expenditure, long income, double taxRate) {
        this.money = money;
        this.expenditure = expenditure;
        this.income = income;
        this.taxRate = taxRate;
    }

    public Budget() {
        this.money = 100000;
        this.expenditure = 10000;
        this.income = 7000;
        this.taxRate = 10.0;
    }

    public void collectTaxes(long populationIncome) {
        money += (taxRate * 0.01) * populationIncome;
    }

    public void increaseExpenditure(long amount){
        expenditure += amount;
    }
    public void decreaseExpenditure(int decreaseBy) {
        if(decreaseBy <= expenditure){
            expenditure -= decreaseBy;
        }
        else{
            System.out.println("Cannot decrease expenditure by " + decreaseBy + " as it exceeds current expenditure of " + expenditure);
        }
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
        this.income = Math.round(population.getPopulationCount() * population.getWorkerPercentage() * (1.+(this.taxRate / 100.0)));
        this.money += this.income - this.expenditure;
    }


}
