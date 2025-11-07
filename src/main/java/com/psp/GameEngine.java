package com.psp;

import com.psp.actions.*;
import com.psp.cityEntities.City;
import com.psp.userInterface.Menu;
import com.psp.Exceptions.*;
import com.psp.userInterface.TerminalRenderer;

import java.util.Scanner;

public class GameEngine {
    int turn = 0;
    City city = new City();
    TurnManager turnManager = new TurnManager();
    Menu menu = new Menu(city);
    Scanner scanner = new Scanner(System.in);
    TerminalRenderer renderer = new TerminalRenderer("mainMenu");
//    menu.startInputLoop();



    void start() {
        while (true) {
            try {
                renderer.clearAndRender(menu.getCurrentMenu());
                String input = scanner.nextLine();
                menu.inputHandler.readMenuInput(menu.getCurrentMenuName(), input);

            } catch (GoBackException goBackException) {
                menu.setCurrentMenuName("mainMenu") ;
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
