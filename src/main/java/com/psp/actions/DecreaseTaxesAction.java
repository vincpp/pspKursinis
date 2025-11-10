package com.psp.actions;

import com.psp.cityEntities.City;

public class DecreaseTaxesAction implements Action{
    int decreaseBy;
    City city;
    public DecreaseTaxesAction(City city, int decreaseBy) {
        this.city = city;
        this.decreaseBy = Math.abs(decreaseBy);
    }
    @Override
    public void execute() {
        if(validate() == true) {
            city.getBudget().decreaseTaxRate(decreaseBy);
            city.refreshIncome();
        }
        else{
            throw new IllegalArgumentException("Cannot Decrease Taxes Below Zero");
        }

    }

    @Override
    public boolean validate() {
        if( city.getBudget().getTaxRate() - decreaseBy < 0){
            System.out.println("Cannot decrease taxes below zero by " + decreaseBy);
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBudget().increaseTaxRate(decreaseBy);
    }
}
