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

    /**
     * Dispatch input based on the provided menu name.
     * Returns an Action when the input produces one, or null when it only changes menu state.
     */
    public Action readMenuInput(MenuName menu, String input) throws GoBackException, ExitProgramException, EndTurnException {
        String cmd = (input == null) ? "" : input.toLowerCase().trim();
        switch (menu) {
            case MAIN -> {
                return readMainMenuInput(cmd);
            }
            case BUILDING -> {
                return readBuildingMenuInput(cmd);
            }
            case ADMINISTRATION -> {
                return readAdministrationMenuInput(cmd);
            }
            default -> {
                System.out.println("Invalid menu: " + menu);
                return null;
            }
        }
    }

    /**
     * Main menu:
     * 1 / v -> go to BUILDING (view / create buildings)
     * 2 / b -> go to BUILDING (new building)
     * 3 / q -> exit program
     */
    public Action readMainMenuInput(String input) throws ExitProgramException, EndTurnException {
        switch (input) {
            case "1", "v" -> parentMenu.setCurrentMenuName(MenuName.ADMINISTRATION);
            case "2", "b" -> parentMenu.setCurrentMenuName(MenuName.BUILDING);
            case "3", "e" -> throw new EndTurnException();
            case "4", "q" -> throw new ExitProgramException();
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    /**
     * Building menu:
     * 1 / h -> build a House (returns BuildAction)
     * 2 / f -> build a Factory (returns BuildAction)
     * 3 / p -> build a Park (returns BuildAction)
     * 4 / w / b -> go back to MAIN
     */
    public Action readBuildingMenuInput(String input) throws GoBackException {
        switch (input) {
            case "1", "h" -> {
                return new BuildAction();
            }
            case "2", "f" -> {
                return new BuildAction();
            }
            case "3", "p" -> {
                return new BuildAction();
            }
            case "4", "w" -> parentMenu.setCurrentMenuName(MenuName.MAIN);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    public Action readAdministrationMenuInput(String input) throws GoBackException {
        switch (input) {
            case "1", "h" -> {
                System.out.print("Input by how much to change taxes (integer, negative to decrease): ");
                String line = scanner.nextLine();
                try {
                    int delta = Integer.parseInt(line.trim());
                    // placeholder action — ModifyTaxesAction can be extended to accept a delta
                    return new ModifyTaxesAction();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + line);
                }
            }
            case "3", "j" -> System.out.println("Manage Happiness selected (not implemented yet).");
            case "4", "k" -> System.out.println("Manage Pollution selected (not implemented yet).");
            case "5", "l" -> System.out.println("Manage Safety selected (not implemented yet).");
            case "6", "w" -> parentMenu.setCurrentMenuName(MenuName.MAIN);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }
}
