package com.psp.actions;

import com.psp.cityEntities.City;

import static java.lang.Math.abs;

public class DecreaseExpenditureAction implements Action{
    int decreaseBy;
    City city;

    public DecreaseExpenditureAction(City city, int decreaseBy) {
        this.city = city;
        this.decreaseBy = abs(decreaseBy);
    }
    @Override
    public void execute() {
        if(validate() == true) {
            city.getBudget().decreasePoliceExpenditure(decreaseBy);
        }
        else{

            System.out.println("Cannot Decrease Expenditure Below Zero");
        }
    }

    @Override
    public boolean validate() {
        if (city.getBudget().getPoliceExpenditure() - decreaseBy < 0) {
            System.out.println("Cannot decrease expenditure below zero by " + decreaseBy);
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBudget().increasePoliceExpenditure(decreaseBy);
    }
}
