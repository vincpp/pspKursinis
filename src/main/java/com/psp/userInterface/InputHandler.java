package com.psp.userInterface;

import com.psp.actions.*;
import com.psp.Exceptions.*;
import com.psp.cityEntities.BuildingType;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class InputHandler {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private Menu parentMenu;

    public InputHandler(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Action readMenuInput(MenuName menu, String input) throws ExitProgramException, EndTurnException {
        String cmd = (input == null) ? "" : input.toLowerCase().trim();
        switch (menu) {
            case MAIN -> {
                return readMainMenuInput(cmd);
            }
            case BUILDING -> {
                return readBuildingMenuInput(cmd);
            }
            case CONSTRUCT_BUILDING -> {
                return readConstructBuildingMenuInput(cmd);
            }
            case FIX_BUILDING -> {
                return readFixBuildingMenuInput(cmd);
            }
            case DESTROY_BUILDING -> {
                return readDestroyBuildingMenuInput(cmd);
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

    public Action readMainMenuInput(String input) throws ExitProgramException, EndTurnException {
        switch (input.toLowerCase().trim()) {
            case "1", "v" -> parentMenu.setCurrentMenuName(MenuName.ADMINISTRATION);
            case "2", "b" -> parentMenu.setCurrentMenuName(MenuName.BUILDING);
            case "3", "e" -> throw new EndTurnException();
            case "4", "q" -> throw new ExitProgramException();
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }


    public Action readBuildingMenuInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> parentMenu.setCurrentMenuName(MenuName.CONSTRUCT_BUILDING);
            case "2", "j" -> parentMenu.setCurrentMenuName(MenuName.FIX_BUILDING);
            case "3", "k" -> parentMenu.setCurrentMenuName(MenuName.DESTROY_BUILDING);
            case "4", "w" -> parentMenu.setCurrentMenuName(MenuName.MAIN);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    // Construction submenu
    public Action readConstructBuildingMenuInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> {
                return new NewBuildingAction(parentMenu.getCity(), BuildingType.RESIDENTIAL);
            }
            case "2", "f" -> {
                return new NewBuildingAction(parentMenu.getCity(), BuildingType.INDUSTRIAL);
            }
            case "3", "c" -> {
                return new NewBuildingAction(parentMenu.getCity(), BuildingType.COMMERCIAL);
            }
            case "4", "p" -> {
                return new NewBuildingAction(parentMenu.getCity(), BuildingType.PUBLIC_SERVICE);
            }
            case "5", "w" -> parentMenu.setCurrentMenuName(MenuName.BUILDING);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    // Fixing submenu
    public Action readFixBuildingMenuInput(String input){
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> {
                return new FixBuildingAction(parentMenu.getCity(), BuildingType.RESIDENTIAL);
            }
            case "2", "f" -> {
                return new FixBuildingAction(parentMenu.getCity(), BuildingType.INDUSTRIAL);
            }
            case "3", "c" -> {
                return new FixBuildingAction(parentMenu.getCity(), BuildingType.COMMERCIAL);
            }
            case "4", "p" -> {
                return new FixBuildingAction(parentMenu.getCity(), BuildingType.PUBLIC_SERVICE);
            }
            case "5", "w" -> parentMenu.setCurrentMenuName(MenuName.BUILDING);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    // Destruction submenu
    public Action readDestroyBuildingMenuInput(String input){
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> {
                return new DestroyBuildingAction(parentMenu.getCity(), BuildingType.RESIDENTIAL);
            }
            case "2", "f" -> {
                return new DestroyBuildingAction(parentMenu.getCity(), BuildingType.INDUSTRIAL);
            }
            case "3", "c" -> {
                return new DestroyBuildingAction(parentMenu.getCity(), BuildingType.COMMERCIAL);
            }
            case "4", "p" -> {
                return new DestroyBuildingAction(parentMenu.getCity(), BuildingType.PUBLIC_SERVICE);
            }
            case "5", "w" -> parentMenu.setCurrentMenuName(MenuName.BUILDING);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }

    public Action readAdministrationMenuInput(String input) {
        switch (input.toLowerCase().trim()) {
            case "1", "h" -> {
                System.out.print("Input by how much to change taxes (integer, negative to decrease): ");
                String line = scanner.nextLine();
                try {
                    int delta = Integer.parseInt(line.trim());
                    // placeholder action — ModifyTaxesAction can be extended to accept a delta
                    if(delta < 0) {
                        return new DecreaseTaxesAction(parentMenu.getCity(), -delta);
                    }
                    else {
                        return new IncreaseTaxesAction(parentMenu.getCity(), delta);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + line);
                }
            }
            case "2", "j" -> {
                System.out.println("Input by how much to change Police funding (integer, negative to decrease):");
                String line = scanner.nextLine();
                try {
                    int delta = Integer.parseInt(line.trim());
                    if(delta > 0) {
                        return new IncreaseExpenditureAction(parentMenu.getCity(), -delta);
                    }
                    else {
                        return new DecreaseExpenditureAction(parentMenu.getCity(), delta);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + line);
                }
            }
            case "3", "w" -> parentMenu.setCurrentMenuName(MenuName.MAIN);
            default -> System.out.println("Invalid input: " + input);
        }
        return null;
    }
}
