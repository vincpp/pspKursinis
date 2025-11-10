package com.psp.userInterface;

import java.io.IOException;

public class TerminalRenderer {
    String currentMenu;
    String currentStats;

    public TerminalRenderer(String currentMenu, String currentStats) {
        this.currentMenu = currentMenu;
        this.currentStats = currentStats;
    }

    public TerminalRenderer() {

    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }
    public void setCurrentStats(String currentStats) {
        this.currentStats = currentStats;
    }

    public void render() {
        System.out.println(currentStats);
        System.out.println(currentMenu);
    }

    public void clearScreen() {

//        try {
//            System.out.print("\033[H\033[2J");
//            System.out.flush();
//            // Give the terminal a moment (often not necessary)
//            return;
//        } catch (Throwable ignored) {
//            System.out.println("Can't use ANSI escape codes to clear the screen.");
//        }

        // Attempt OS-specific clear command
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("powershell", "-command", "Clear-Host").inheritIO().start().waitFor();;
            } else {
                // Unix-like
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            return;
        } catch (IOException | InterruptedException ignored) {
            System.out.println("Couldn't clear the terminal screen.");
        }

    }

    public void clearAndRender(String newMenu, String newStats, int turnNumber) {
        setCurrentStats(newStats);
        setCurrentMenu(newMenu);
        clearScreen();
        System.out.println("TURN: " + turnNumber);
        render();
    }
}
