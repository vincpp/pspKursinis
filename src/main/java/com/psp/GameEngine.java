package com.psp;

import com.psp.actions.*;
import com.psp.cityEntities.City;
import com.psp.cityEntities.CityStats;
import com.psp.userInterface.Menu;
import com.psp.Exceptions.*;
import com.psp.userInterface.MenuName;
import com.psp.userInterface.TerminalRenderer;

import java.util.Scanner;

public class GameEngine {
    City city = new City();
    City nextTurnCity = new City();
    CityStats cityStats = new CityStats(city, nextTurnCity);
    TurnManager turnManager = new TurnManager(city, nextTurnCity);
    Menu menu = new Menu(nextTurnCity); // needs next turn city to be able to rollback
    Scanner scanner = new Scanner(System.in);
    TerminalRenderer renderer = new TerminalRenderer();
//    menu.startInputLoop();

    public GameEngine() {
        nextTurnCity.updateForNextTurn();
    }

    void start() {
        while (true) {
            try {
                renderer.clearAndRender(menu.getCurrentMenu(), cityStats.getStats(), turnManager.getTurn());
                String input = scanner.nextLine();

                Action action = menu.getInputHandler().readMenuInput(menu.getCurrentMenuName(), input);
                if (action != null) {
                    handlePlayerAction(action);
                }

            }
            catch (EndTurnException e){
                turnManager.advanceTurn();
            }
//            catch (GoBackException goBackException) {
//                menu.setCurrentMenuName(MenuName.MAIN) ;
//            }
            catch (ExitProgramException exitProgramException) {
                System.out.println("Exiting program...");
                System.exit(0);
            }
        }
    }


    void advanceTurn(){

    }
    void handlePlayerAction(Action action){
        try {
            action.execute();
        }
        catch (Exception e){
            System.out.println("An error occurred while executing the action: " + e.getMessage());
            e.printStackTrace();
        }

    }
    void resolveEvents(){}


}
