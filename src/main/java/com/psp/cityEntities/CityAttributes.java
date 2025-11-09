package com.psp.cityEntities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityAttributes {
    private int happiness;
    private int pollution;
    private int safety;

    public CityAttributes() {
        this.happiness = 50;
        this.pollution = 50;
        this.safety = 50;

    }

    private double getHappinessIndex() {
        return ((pollution * -0.5) + (safety * 0.5)) / 100.0; // higher safety raises happiness, higher pollution lowers it
    }

    private double getSafetyIndex() {
        return (100 - pollution) / 100.0; // safety index inversely related to pollution (simple)
    }
    public void updateForNextTurn(Population population, BuildingManager bm, Budget budget) {
        if (population == null || bm == null || budget == null) return;

        // Pollution: industrial and commercial add pollution, public services reduce it, and there's a small natural decay
        double pollutionDelta = bm.industrialBuildings * 1.5 + bm.commercialBuildings * 0.5 - bm.publicServiceBuildings * 1.0 - this.pollution * 0.01;

        // Safety: public services increase safety, pollution lowers it, and larger population slightly reduces safety
        double safetyDelta = bm.publicServiceBuildings * 0.8 - (population.getPopulation() / 1000.0) * 0.02 - this.pollution * 0.03;

        // Happiness: increased by public services and a healthy economy, decreased by tax rate and pollution
        double econEffect = (budget.getIncome() - budget.getExpenditure()) / 10000.0; // scaled
        double happinessDelta = bm.publicServiceBuildings * 0.4 + econEffect * 0.2 - budget.getTaxRate() * 0.05 - this.pollution * 0.08;

        // Apply changes and clamp to [0,100]
        this.pollution = clamp((int) Math.round(this.pollution + pollutionDelta), 0, 100);
        this.safety = clamp((int) Math.round(this.safety + safetyDelta), 0, 100);
        this.happiness = clamp((int) Math.round(this.happiness + happinessDelta), 0, 100);
    }

    private int clamp(int v, int min, int max) {
        if (v < min) return min;
        if (v > max) return max;
        return v;
    }


}
