package com.psp.cityEntities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Budget {
    long money=0;
    long income=0;
    long expenditure=0;
    double taxRate=0;

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

    void collectTaxes(long populationIncome) {
        money += (taxRate * 0.01) * populationIncome;
    }

    void cutExpenditure(long amount){
        expenditure -= amount;
    }

    void increaseTaxRate(int amount){
        taxRate += amount;
    }
    void decreaseTaxRate(int amount){
        taxRate -= amount;
    }

    public void updateForNextTurn(Population population) {
        if (population.getPopulation() < 0) return;
        this.income = Math.round(population.getPopulation() * population.getWorkerPercentage() * (1.+(this.taxRate / 100.0)));
        this.money += this.income - this.expenditure;
    }

}
