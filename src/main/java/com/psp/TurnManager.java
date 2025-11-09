package com.psp;

import com.psp.cityEntities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnManager
{
    private int turn;
    private City currentTurn;
    private City nextTurn;
    public TurnManager(City currentTurn, City NextTurn)
    {
        this.currentTurn = currentTurn;
        this.nextTurn = NextTurn;
        this.turn = 1;
    }

    public void advanceTurn()
    {
//        City temp = currentTurn;
        nextTurn.updateForNextTurn();
        currentTurn.updateCity(nextTurn);
//        nextTurn = currentTurn;
        turn++;
        checkIsGameLost();
        checkIsGameWon();

    }
//    private void updateToProjectedValues
    private void checkIsGameLost(){
        if(currentTurn.getBudget().getMoney() < 0){
            System.out.println("Game Over! You have run out of money.");
            System.exit(0);
        }
        else if(currentTurn.getPopulation().getPopulationCount() <= 100){
            System.out.println("Game Over! Your city has been abandoned due to lack of population.");
            System.exit(0);
        }
        else if(currentTurn.getAttributes().getHappiness() <= 0){
            System.out.println("Game Over! Your citizens are too unhappy to continue living in your city.");
            System.exit(0);
        }
        else if(currentTurn.getAttributes().getSafety() <= 0){
            System.out.println("Game Over! Your city has become too unsafe for its citizens.");
            System.exit(0);
        }
        else if(currentTurn.getAttributes().getPollution() >= 100){
            System.out.println("Game Over! Pollution levels in your city have reached a critical level.");
            System.exit(0);
        }
    }
    private void checkIsGameWon(){
        if(currentTurn.getPopulation().getPopulationCount() >= 1000000){
            System.out.println("Congratulations! You have built a thriving city with a population of over 1 million!");
            System.exit(0);
        }
        else if(currentTurn.getBudget().getMoney()>=1000000 ){
            System.out.println("Congratulations! You have amassed a budget of over 1 million!");
            System.exit(0);
        }
        else if(currentTurn.getAttributes().getHappiness()>=90 && currentTurn.getAttributes().getSafety()>=90 && currentTurn.getAttributes().getPollution()<=10 && currentTurn.getPopulation().getPopulationCount()>=50000){
            System.out.println("Congratulations! You have created a utopian city with high happiness and safety, and low pollution!");
            System.exit(0);
        }
    }
}
