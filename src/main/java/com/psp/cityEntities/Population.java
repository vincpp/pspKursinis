package com.psp.cityEntities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Population {
    private long populationCount;
    private double growthRate;
    private double workerPercentage;

    public Population(){
        populationCount = 1000;
        growthRate = 2;
        workerPercentage= 0.80;
    }
    public Population(long populationCount, double growthRate, double workerPercentage){
        this.populationCount = populationCount;
        this.growthRate = growthRate;
        this.workerPercentage = workerPercentage;
    }

    public void modifyGrowthRate(int delta){
        growthRate += delta / 100.0;
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
        if (populationCount < 0) populationCount = 0;

    }

}
