package com.psp.cityEntities;


import lombok.Getter;
import lombok.Setter;
import lombok.Setter;

@Setter
@Getter
public class Population {
    private long population;
    private double growthRate;
    private double workerPercentage= 0.85;

    public Population(){
        population = 10000;
        growthRate = 2;
    }
    public Population(long population, double growthRate){
        this.population = population;
        this.growthRate = growthRate;
    }
    public void growPopulation(){
        population = (long)(population * (1. + growthRate/100.0));
    }


    public long getWorkingPopulation() {
        return Math.round(this.population * this.workerPercentage);
    }

    public void updateForNextTurn() {

        if (population < 0) population = 0;
        growPopulation();
        if (population < 0) population = 0; // guard again in case of overflow (defensive)
        if (workerPercentage < 0) workerPercentage = 0;
        if (workerPercentage > 1) workerPercentage = 1;
    }

}
