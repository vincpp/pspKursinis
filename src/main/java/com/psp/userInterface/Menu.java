package com.psp.userInterface;

import com.psp.cityEntities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private City city;
    private CityStats cityStats;
    private MenuName currentMenuName = MenuName.MAIN;
    public InputHandler inputHandler;
//    Scanner scanner = new Scanner(System.in);
//    Action currentAction = new Action();
    private String mainMenu =
            "=========================\n" +
            "      MAIN MENU         \n" +
            "=========================\n" +
            "1. Administration Menu (V)\n" +
            "2. New Building (B)\n" +
            "3. End Turn (E) \n" +
            "4. Exit (Q) \n" +
            "=========================\n" +
            "Please select an option: ";

    private String buildingMenu =
            "=========================\n" +
            "    BUILDING MENU       \n" +
            "=========================\n" +
            "1. House (H)\n" +
            "2. Factory (F)\n" +
            "3. Park (P) \n" +
            "4. Back (W) \n" +
            "=========================\n" +
            "Please select a building type: ";

    private String administrationMenu=
            "=========================\n" +
            "  ADMINISTRATION MENU   \n" +
            "=========================\n" +
            "1. Change Taxes (H)\n" +
            "2. Manage Happiness(J) \n" +
            "3. Manage Pollution(K) \n" +
            "4. Manage Safety(L) \n" +
            "5. Back (W) \n" +
            "=========================\n" +
            "Please select an option: ";

//    Action playerAction = inputHandler.readMenuInput();
    public String getCurrentMenu() {
        return switch (currentMenuName) {
            case MAIN -> mainMenu;
            case BUILDING -> buildingMenu;
            case ADMINISTRATION -> administrationMenu;
        };
    }

    public String getCityStats(){
        return cityStats.getStats();
    }


    public Menu(City city) {
        this.city = city;
        inputHandler= new InputHandler(this);
        this.cityStats = new CityStats(city);
    }


}