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
        // Try ANSI escape first
        final String ANSI_CLS = "\u001b[2J\u001b[H";
        try {
            System.out.print(ANSI_CLS);
            System.out.flush();
            // Give the terminal a moment (often not necessary)
            return;
        } catch (Throwable ignored) {
            // ignore and try other options
        }

        // Attempt OS-specific clear command
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-like
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
            return;
        } catch (IOException | InterruptedException ignored) {
            // ignore and fallback to newline
            Thread.currentThread().interrupt();
        } catch (Throwable ignored) {
            // ignore any other issues
        }

    }

    public void clearAndRender(String newMenu, String newStats, int turnNumber) {
        setCurrentStats(newStats);
        System.out.println("TURN: " + turnNumber);
        setCurrentMenu(newMenu);
        clearScreen();
        render();
    }
}
