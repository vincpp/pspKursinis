package com.psp.cityEntities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Population {
    private long populationCount;
    private double growthRate;
    private double workerPercentage= 0.85;

    public Population(){
        populationCount = 10000;
        growthRate = 2;
    }
    public Population(long populationCount, double growthRate){
        this.populationCount = populationCount;
        this.growthRate = growthRate;
    }
    public void growPopulation(){
        populationCount = (long)(populationCount * (1. + growthRate/100.0));
    }


    public long getWorkingPopulation() {
        return Math.round(this.populationCount * this.workerPercentage);
    }

    public void updateForNextTurn() {

        if (populationCount < 0) populationCount = 0;
        growPopulation();
        if (populationCount < 0) populationCount = 0; // guard again in case of overflow (defensive)
        if (workerPercentage < 0) workerPercentage = 0;
        if (workerPercentage > 1) workerPercentage = 1;
    }

}
