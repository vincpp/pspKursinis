package com.psp.userInterface;

import com.psp.cityEntities.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private City city;
    private MenuName currentMenuName = MenuName.MAIN;
    public InputHandler inputHandler;
//    Scanner scanner = new Scanner(System.in);
//    Action currentAction = new Action();
    private String mainMenu =
            "=========================\n" +
                    "      MAIN MENU         \n" +
                    "=========================\n" +
                    "1. View Buildings (V)\n" +
                    "2. New Building (B)\n" +
                    "3. Exit (Q) \n" +
                    "=========================\n" +
                    "Please select an option: ";
    private String buildingMenu =
            "=========================\n" +
                    "    BUILDING MENU       \n" +
                    "=========================\n" +
                    "1. House (H)\n" +
                    "2. Factory (F)\n" +
                    "3. Park (P) \n" +
                    "4. Back (B) \n" +
                    "=========================\n" +
                    "Please select a building type: ";
    private String administrationMenu=
            "=========================\n" +
                    "  ADMINISTRATION MENU   \n" +
                    "=========================\n" +
                    "1. Change Taxes (S)\n" +
                    "2. Cut Expenditure on Waste \n" +
                    "2. Cut Expenditure on Happiness \n" +
                    "3. Back (B) \n" +
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



    public Menu(City city) {

        this.city = city;
        inputHandler= new InputHandler(this);
    }


}