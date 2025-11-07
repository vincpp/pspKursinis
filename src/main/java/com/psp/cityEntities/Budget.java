package com.psp.cityEntities;

public class Budget {
    long money=0;
    long expenditure=0;
    double taxRate=0;


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

}
