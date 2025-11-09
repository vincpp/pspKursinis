package com.psp.userInterface;

import com.psp.cityEntities.City;

public class CityStats {
    City city;
    City nextTurnCity;
    public CityStats(City city){
        this.city = city;

    }

    public String getStats(){
        if (city == null) {
            return "City not available." + System.lineSeparator();
        }

        // create a deep copy and compute next-turn projection
        this.nextTurnCity = new City(this.city);
        this.nextTurnCity.updateForNextTurn();

         // ANSI color codes
         final String RESET = "\u001B[0m";
         final String RED = "\u001B[31m";
         final String YELLOW = "\u001B[33m";
         final String GREEN = "\u001B[32m";

        // Current values
        long currentPop = city.getPopulation();
        double currentGrowth = city.getPopulationGrowthRate();
        double currentTax = city.getTaxRate();
        long currentMoney = city.getBudgetMoney();
        long currentIncome = city.getBudgetIncome();
        long currentExpenditure = city.getBudgetExpenditure();
        int happiness = city.getHappiness();
        int pollution = city.getPollution();
        int safety = city.getSafety();

        // Projected values (from deep copy)
        long projectedPop = nextTurnCity.getPopulation();
        double projectedGrowth = nextTurnCity.getPopulationGrowthRate();
        double projectedTax = nextTurnCity.getTaxRate();
        long projectedMoney = nextTurnCity.getBudgetMoney();
        long projectedIncome = nextTurnCity.getBudgetIncome();
        long projectedExpenditure = nextTurnCity.getBudgetExpenditure();
        int projectedHappiness = nextTurnCity.getHappiness();
        int projectedPollution = nextTurnCity.getPollution();
        int projectedSafety = nextTurnCity.getSafety();

        // Current attribute colors
        String colorH = (happiness < 30) ? RED : (happiness < 70) ? YELLOW : GREEN;
        String colorP = (pollution < 30) ? RED : (pollution < 70) ? YELLOW : GREEN;
        String colorS = (safety < 30) ? RED : (safety < 70) ? YELLOW : GREEN;

        // Trend colors comparing projected vs current
        String projHTrendColor = projectedHappiness > happiness ? GREEN : (projectedHappiness < happiness ? RED : YELLOW);
        String projPTrendColor = projectedPollution > pollution ? GREEN : (projectedPollution < pollution ? RED : YELLOW);
        String projSTrendColor = projectedSafety > safety ? GREEN : (projectedSafety < safety ? RED : YELLOW);

        String projMoneyColor = projectedMoney > currentMoney ? GREEN : (projectedMoney < currentMoney ? RED : YELLOW);
        String projIncomeColor = projectedIncome > currentIncome ? GREEN : (projectedIncome < currentIncome ? RED : YELLOW);
        String projExpenditureColor = projectedExpenditure > currentExpenditure ? GREEN : (projectedExpenditure < currentExpenditure ? RED : YELLOW);

        String projPopColor = projectedPop > currentPop ? GREEN : (projectedPop < currentPop ? RED : YELLOW);
        String projGrowthColor = projectedGrowth > currentGrowth ? GREEN : (projectedGrowth < currentGrowth ? RED : YELLOW);

        StringBuilder sb = new StringBuilder();

        // Line 1: Population | Happiness | Money
        sb.append("Population: ").append(currentPop)
          .append(" --> ").append(projPopColor).append(projectedPop).append(RESET)
          .append("\t\tHappiness: ").append(colorH).append(happiness).append(RESET)
          .append(" --> ").append(projHTrendColor).append(projectedHappiness).append(RESET)
          .append("\t\tMoney: ").append(currentMoney).append("€")
          .append(" --> ").append(projMoneyColor).append(projectedMoney).append(RESET).append("€")
          .append(System.lineSeparator());

        // Line 2: Growth | Pollution | Income
        sb.append("Growth rate: ").append(String.format("%.2f", currentGrowth)).append("%")
          .append(" --> ").append(projGrowthColor).append(String.format("%.2f", projectedGrowth)).append("%").append(RESET)
          .append("\tPollution: ").append(colorP).append(pollution).append(RESET)
          .append(" --> ").append(projPTrendColor).append(projectedPollution).append(RESET)
          .append("\t\tIncome: ").append(currentIncome).append("€")
          .append(" --> ").append(projIncomeColor).append(projectedIncome).append(RESET).append("€")
          .append(System.lineSeparator());

        // Line 3: Tax | Safety | Expenditure
        sb.append("Tax rate: ").append(String.format("%.2f%%", currentTax))
          .append(" --> ").append(YELLOW).append(String.format("%.2f%%", projectedTax)).append(RESET)
          .append("\t\tSafety: ").append(colorS).append(safety).append(RESET)
          .append(" --> ").append(projSTrendColor).append(projectedSafety).append(RESET)
          .append("\t\t\tExpenditure: ").append(currentExpenditure).append("€")
          .append(" --> ").append(projExpenditureColor).append(projectedExpenditure).append(RESET).append("€")
          .append(System.lineSeparator());

        return sb.toString();
    }
}