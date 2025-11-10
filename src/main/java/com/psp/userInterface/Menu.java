package com.psp.userInterface;

import com.psp.cityEntities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private City city;
    private City nextTurnCity;
    private MenuName currentMenuName = MenuName.MAIN;
    private InputHandler inputHandler;

    private String mainMenu =
            "=========================\n" +
            "      MAIN MENU         \n" +
            "=========================\n" +
            "1. Administration Menu (V)\n" +
            "2. Building Menu (B)\n" +
            "3. End Turn (E) \n" +
            "4. Exit (Q) \n" +
            "=========================\n" +
            "Please select an option: ";

    private String buildingMenu =
            "=========================\n" +
            "       BUILDING MENU       \n" +
            "=========================\n" +
            "1. Build New Buildings (H)\n" +
            "2. Fix Damaged Buildings (J)\n" +
            "3. Destroy Buildings (K)\n" +
            "4. Back (W) \n" +
            "=========================\n" +
            "Please select a building type: ";

    private String fixBuildingMenu =
            "=========================\n" +
            "   BUILDING FIXING MENU       \n" +
            "=========================\n" +
            "1. Fix a House (H)\n" +
            "2. Fix a Factory (F)\n" +
            "3. Fix a Commercial Building (C)\n" +
            "4. Fix a Park (P) \n" +
            "5. Back (W) \n" +
            "=========================\n" +
            "Please select a building type: ";

    private String constructBuildingMenu =
            "=========================\n" +
            "    CONSTRUCTION MENU       \n" +
            "=========================\n" +
            "1. House (H)           -5000€ \n" +
            "2. Factory (F)         -20000€ \n" +
            "3. Commercial (C)      -50000€\n" +
            "4. Public Service (P)  -35000€\n" +
            "5. Back (W) \n" +
            "=========================\n" +
            "Please select a building type to build: ";

    private String destroyBuildingMenu =
            "=========================\n" +
            "    DECONSTRUCTION MENU       \n" +
            "=========================\n" +
            "1. DESTROY a House (H)\n" +
            "2. DESTROY a Factory (F)\n" +
            "3. DESTROY a Commercial (C)\n" +
            "4. DESTROY a Park (P) \n" +
            "5. Back (W) \n" +
            "=========================\n" +
            "Please select a building type to build: ";

    private String administrationMenu=
            "=========================\n" +
            "  ADMINISTRATION MENU   \n" +
            "=========================\n" +
            "1. Change Taxes (H)\n" +
            "2. Manage Police Expenditure\n" +
            "3. Back (W) \n" +
            "=========================\n" +
            "Please select an option: ";

//    Action playerAction = inputHandler.readMenuInput();
    public String getCurrentMenu() {
        return switch (currentMenuName) {
            case MAIN -> mainMenu;
            case BUILDING -> buildingMenu;
            case FIX_BUILDING -> fixBuildingMenu;
            case CONSTRUCT_BUILDING -> constructBuildingMenu;
            case DESTROY_BUILDING -> destroyBuildingMenu;
            case ADMINISTRATION -> administrationMenu;
        };
    }

    public Menu(City city) {
        this.city = city;
        inputHandler= new InputHandler(this);
    }


}