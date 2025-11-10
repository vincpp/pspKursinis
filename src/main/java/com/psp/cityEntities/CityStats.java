package com.psp.cityEntities;

public class CityStats {
    City city;
    City nextTurnCity;
    public CityStats(City city, City nextTurnCity) {
        this.city = city;
        this.nextTurnCity = nextTurnCity;

    }

    public String getStats(){
        if (city == null) {
            return "City not available." + System.lineSeparator();
        }

         // ANSI color codes
         final String RESET = "\u001B[0m";
         final String RED = "\u001B[31m";
         final String YELLOW = "\u001B[33m";
         final String GREEN = "\u001B[32m";

        // Current values

        BuildingManager buildingManager = city.getBuildingManager();

        long currentPop = city.getPopulation().getPopulationCount();
        double currentGrowth = city.getPopulation().getGrowthRate();
        double currentTax = city.getBudget().getTaxRate();
        long currentMoney = city.getBudget().getMoney();
        long currentIncome = city.getBudget().getIncome();
        long currentExpenditure = city.getBudget().getExpenditure();
        long currentPoliceExpenditure = city.getBudget().getPoliceExpenditure();
        long currentServiceBuildingUpkeep = city.getBudget().getServiceBuildingUpkeep();
        int happiness = city.getAttributes().getHappiness();
        int pollution = city.getAttributes().getPollution();
        int safety = city.getAttributes().getSafety();

        int curResA = buildingManager.getActiveBuildingCount(BuildingType.RESIDENTIAL);
        int curResI = buildingManager.getInactiveBuildingCount(BuildingType.RESIDENTIAL);
        int curComA = buildingManager.getActiveBuildingCount(BuildingType.COMMERCIAL);
        int curComI = buildingManager.getInactiveBuildingCount(BuildingType.COMMERCIAL);
        int curIndA = buildingManager.getActiveBuildingCount(BuildingType.INDUSTRIAL);
        int curIndI = buildingManager.getInactiveBuildingCount(BuildingType.INDUSTRIAL);
        int curPubA = buildingManager.getActiveBuildingCount(BuildingType.PUBLIC_SERVICE);
        int curPubI = buildingManager.getInactiveBuildingCount(BuildingType.PUBLIC_SERVICE);



        // Projected values (from deep copy)
        BuildingManager projectedBM = nextTurnCity.getBuildingManager();
        long projectedPop = nextTurnCity.getPopulation().getPopulationCount();
        double projectedGrowth = nextTurnCity.getPopulation().getGrowthRate();
        double projectedTax = nextTurnCity.getBudget().getTaxRate();
        long projectedMoney = nextTurnCity.getBudget().getMoney();
        long projectedIncome = nextTurnCity.getBudget().getIncome();
        long projectedExpenditure = nextTurnCity.getBudget().getExpenditure();

        long projectedPoliceExpenditure = nextTurnCity.getBudget().getPoliceExpenditure();
        long projectedServiceBuildingUpkeep = nextTurnCity.getBudget().getServiceBuildingUpkeep();
        int projectedHappiness = nextTurnCity.getAttributes().getHappiness();
        int projectedPollution = nextTurnCity.getAttributes().getPollution();
        int projectedSafety = nextTurnCity.getAttributes().getSafety();

        long projNetIncome = (projectedIncome - projectedExpenditure);

        int projResA = projectedBM.getActiveBuildingCount(BuildingType.RESIDENTIAL);
        int projResI = projectedBM.getInactiveBuildingCount(BuildingType.RESIDENTIAL);
        int projComA = projectedBM.getActiveBuildingCount(BuildingType.COMMERCIAL);
        int projComI = projectedBM.getInactiveBuildingCount(BuildingType.COMMERCIAL);
        int projIndA = projectedBM.getActiveBuildingCount(BuildingType.INDUSTRIAL);
        int projIndI = projectedBM.getInactiveBuildingCount(BuildingType.INDUSTRIAL);
        int projPubA = projectedBM.getActiveBuildingCount(BuildingType.PUBLIC_SERVICE);
        int projPubI = projectedBM.getInactiveBuildingCount(BuildingType.PUBLIC_SERVICE);

        int curUsedSlots = curResA + curResI + curComA + curComI + curIndA + curIndI + curPubA + curPubI;
        int projUsedSlots = projResA + projResI + projComA + projComI + projIndA + projIndI + projPubA + projPubI;
        int landSlots = buildingManager.getLandSlots();
        int projLandSlots = projectedBM.getLandSlots();



        // Current attribute colors
        String colorH = (happiness < 30) ? RED : (happiness < 70) ? YELLOW : GREEN;
        String colorP = (pollution < 30) ? GREEN : (pollution < 70) ? YELLOW : RED;
        String colorS = (safety < 30) ? RED : (safety < 70) ? YELLOW : GREEN;

        // Trend colors comparing projected vs current
        String projHTrendColor = projectedHappiness > happiness ? GREEN : (projectedHappiness < happiness ? RED : YELLOW);
        String projPTrendColor = projectedPollution < pollution ? GREEN : (projectedPollution > pollution ? RED : YELLOW);
        String projSTrendColor = projectedSafety > safety ? GREEN : (projectedSafety < safety ? RED : YELLOW);

        String projMoneyColor = projectedMoney > currentMoney ? GREEN : (projectedMoney < currentMoney ? RED : YELLOW);
        String projIncomeColor = projectedIncome > currentIncome ? GREEN : (projectedIncome < currentIncome ? RED : YELLOW);
        String projExpenditureColor = projectedExpenditure < currentExpenditure ? GREEN : (projectedExpenditure > currentExpenditure ? RED : YELLOW);
        String projNetIncomeColor = (projectedIncome - projectedExpenditure) > 0 ? GREEN : (projectedIncome - projectedExpenditure < 0 ? RED : YELLOW);

        String projPopColor = projectedPop > currentPop ? GREEN : (projectedPop < currentPop ? RED : YELLOW);
        String projGrowthColor = projectedGrowth > currentGrowth ? GREEN : (projectedGrowth < currentGrowth ? RED : YELLOW);


        // Colors for projected building counts
        // Active: green if increase, yellow if same, red if decrease
        String resAColor = projResA > curResA ? GREEN : (projResA < curResA ? RED : YELLOW);
        String comAColor = projComA > curComA ? GREEN : (projComA < curComA ? RED : YELLOW);
        String indAColor = projIndA > curIndA ? GREEN : (projIndA < curIndA ? RED : YELLOW);
        String pubAColor = projPubA > curPubA ? GREEN : (projPubA < curPubA ? RED : YELLOW);

        // Inactive: red if increase, yellow if same, green if decrease
        String resIColor = projResI > curResI ? RED : (projResI < curResI ? GREEN : YELLOW);
        String comIColor = projComI > curComI ? RED : (projComI < curComI ? GREEN : YELLOW);
        String indIColor = projIndI > curIndI ? RED : (projIndI < curIndI ? GREEN : YELLOW);
        String pubIColor = projPubI > curPubI ? RED : (projPubI < curPubI ? GREEN : YELLOW);


        int curResT = curResA + curResI;
        int projResT = projResA + projResI;

        int curComT = curComA + curComI;
        int projComT = projComA + projComI;

        int curIndT = curIndA + curIndI;
        int projIndT = projIndA + projIndI;

        int curPubT = curPubA + curPubI;
        int projPubT = projPubA + projPubI;

        StringBuilder sb = new StringBuilder();

        // Line 1: Population | Happiness | Money
        sb.append("Population: ").append(currentPop)
          .append(" --> ").append(projPopColor).append(projectedPop).append(RESET)
          .append("\t\tHappiness: ").append(colorH).append(happiness).append(RESET)
          .append(" --> ").append(projHTrendColor).append(projectedHappiness).append(RESET)
          .append("\t\tMoney: ").append(currentMoney).append("€")
          .append(" --> ").append(projMoneyColor).append(projectedMoney).append(RESET).append("€")
          .append("\t(").append(projNetIncomeColor).append(projNetIncome).append(RESET).append("€)")
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
          .append("\t\t\tExpenditure: ").append(currentExpenditure).append("€ (").append(currentPoliceExpenditure).append("€ + ").append(currentServiceBuildingUpkeep).append("€)")
          .append(" --> ").append(projExpenditureColor).append(projectedExpenditure).append(RESET).append("€")
            .append(" (").append(projectedPoliceExpenditure).append("€ + ").append(projectedServiceBuildingUpkeep).append("€)")
          .append(System.lineSeparator());


        // Buildings section with totals [Active/Inactive \[Total]]
        sb.append("\nBuildings (Active/Inactive [Total])  \nResidential: \t\t")
            .append(curResA).append("/").append(curResI).append(" [").append(curResT).append("] -> ")
            .append(resAColor).append(projResA).append(RESET).append("/")
            .append(resIColor).append(projResI).append(RESET)
            .append(" [").append(projResT).append("]")

            .append("  \nCommercial: \t\t").append(curComA).append("/").append(curComI).append(" [").append(curComT).append("] -> ")
            .append(comAColor).append(projComA).append(RESET).append("/")
            .append(comIColor).append(projComI).append(RESET)
            .append(" [").append(projComT).append("]")

            .append("  \nIndustry: \t\t\t").append(curIndA).append("/").append(curIndI).append(" [").append(curIndT).append("] -> ")
            .append(indAColor).append(projIndA).append(RESET).append("/")
            .append(indIColor).append(projIndI).append(RESET)
            .append(" [").append(projIndT).append("]")

            .append("  \nPublic Service: \t").append(curPubA).append("/").append(curPubI).append(" [").append(curPubT).append("] -> ")
            .append(pubAColor).append(projPubA).append(RESET).append("/")
            .append(pubIColor).append(projPubI).append(RESET)
            .append(" [").append(projPubT).append("]")
            .append(System.lineSeparator());
        sb.append("\nLand slots used/total: ")
            .append(curUsedSlots).append("/").append(landSlots)
            .append(" -> ")
            .append(projUsedSlots).append("/").append(projLandSlots)
            .append(System.lineSeparator());

        return sb.toString();
    }
}