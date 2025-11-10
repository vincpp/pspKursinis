package com.psp.actions;

import com.psp.cityEntities.City;

import static java.lang.Math.abs;

public class IncreaseExpenditureAction implements Action{
    int increaseBy;
    City city;

    public IncreaseExpenditureAction(City city, int increaseBy) {
        this.city = city;
        this.increaseBy = abs(increaseBy);
    }

    @Override
    public void execute() {
        if(validate() == true) {
            city.getBudget().increasePoliceExpenditure(increaseBy);
//            city.getBudget().refreshExpenditure();
        }
        else{
            throw new IllegalArgumentException("Not Enough Budget to Increase Expenditure");
        }

    }

    @Override
    public boolean validate() {
        if (city.getBudget().getMoney() < increaseBy) {
            System.out.println("Not enough budget to increase expenditure by " + increaseBy);
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBudget().decreasePoliceExpenditure(increaseBy);
    }

}
