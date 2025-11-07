package com.psp.userInterface;

import com.psp.actions.*;
import com.psp.Exceptions.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class InputHandler {
    Scanner scanner = new Scanner(System.in);
    String currentInput;
    Menu parentMenu;

    public InputHandler(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Action readMenuInput(MenuName menu, String input) throws GoBackException, ExitProgramException {
        switch (menu) {
            case MAIN -> {
                return readMainMenuInput(input);
            }
            case BUILDING -> {
                return readBuildingMenuInput(input);
            }
            case ADMINISTRATION -> {
                return readAdministrationMenuInput(input);
            }
            default -> {
                System.out.println("Invalid menu: " + menu);
                return null;
            }
        }
    }

    public Action readMainMenuInput(String input) throws ExitProgramException {
        switch (input.toLowerCase().trim()) {
            case "1", "v" -> {
                parentMenu.setCurrentMenuName(MenuName.ADMINISTRATION);
//                return;
            }
            case "2", "b" -> {
                parentMenu.setCurrentMenuName(MenuName.BUILDING);
                break;
            }
            case "3", "q" -> {
                throw (new ExitProgramException());

            }
            default -> {
                // Invalid Input
                System.out.println("Invalid input: " + input);
                break;
            }
        }
        return null;
    }


    public Action readBuildingMenuInput(String input) throws GoBackException {
        switch (input.toLowerCase().trim()) {
            case "1", "v" -> {
//                return;
            }
            case "2", "b" -> {
                parentMenu.setCurrentMenuName(MenuName.MAIN);
                break;
            }
            case "3", "w" -> {
                throw(new GoBackException());
            }
            default -> {
                // Invalid Input
                System.out.println("Invalid input: " + input);
                break;
            }
        }
        return null;
    }

    public Action readAdministrationMenuInput(String input) throws GoBackException {
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> {
                System.out.println("Input by how much to increase(positive number) /decrease(negative number) taxes: ");
//                int in = scanner.nextLine();
//                if(in < 0){
//                    return new ModifyTaxesAction(in);
//                } else {
//                    return new IncreaseTaxesAction(in);
//                break;
//                }
//              return ModifyTaxesAction;
                break;
            }
            case "2", "j" -> {
                System.out.println("Input by how much to increase(positive number) /decrease(negative number) taxes: ");
                break;
            }
            case "3", "w" -> {
                throw(new GoBackException());
            }
            default -> {
                // Invalid Input
                System.out.println("Invalid input: " + input);
                break;
            }
        }
        return null;
    }
}
