package com.psp;

import com.psp.actions.*;
import com.psp.cityEntities.City;
import com.psp.userInterface.Menu;
import com.psp.Exceptions.*;
import com.psp.userInterface.MenuName;
import com.psp.userInterface.TerminalRenderer;

import java.util.Scanner;

public class GameEngine {
    City city = new City();
    City nextTurnCity = new City();
    TurnManager turnManager = new TurnManager(city, nextTurnCity);
    Menu menu = new Menu(city);
    Scanner scanner = new Scanner(System.in);
    TerminalRenderer renderer = new TerminalRenderer();
//    menu.startInputLoop();



    void start() {
        while (true) {
            try {
                renderer.clearAndRender(menu.getCurrentMenu(), menu.getCityStats(), TurnManager.getTurn);
                String input = scanner.nextLine();
                menu.inputHandler.readMenuInput(menu.getCurrentMenuName(), input);

            }
            catch (EndTurnException e){
                turnManager.advanceTurn();
            }
            catch (GoBackException goBackException) {
                menu.setCurrentMenuName(MenuName.MAIN) ;
            } catch (ExitProgramException exitProgramException) {
                System.out.println("Exiting program...");
                System.exit(0);
            }
        }
    }


    void advanceTurn(){

    }
    void handlePlayerAction(Action action){

    }
    void resolveEvents(){}


}
