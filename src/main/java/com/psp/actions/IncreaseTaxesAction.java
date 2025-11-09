package com.psp.actions;

import com.psp.cityEntities.City;

public class IncreaseTaxesAction implements Action{
    int increaseBy;
    City city;

    public IncreaseTaxesAction(City city, int increaseBy) {
        this.city = city;
        this.increaseBy = Math.abs(increaseBy);
    }
    @Override
    public void execute() {
        if(validate()==true){
            city.getBudget().increaseTaxRate(increaseBy);
        }
        else{
            throw new IllegalArgumentException("Cannot Increase Taxes Above 100");
        }
    }

    @Override
    public boolean validate() {
        if(city.getBudget().getTaxRate() + increaseBy > 100){
            System.out.println("Cannot increase taxes above 100 by " + increaseBy);
            return false;
        }
        return true;
    }

    @Override
    public void undo() {
        city.getBudget().decreaseTaxRate(increaseBy);
    }
}
