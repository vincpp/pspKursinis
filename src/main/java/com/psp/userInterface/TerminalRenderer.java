package com.psp.userInterface;

import java.io.IOException;

public class TerminalRenderer {
    String currentMenu;

    public TerminalRenderer(String currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void render() {
        System.out.println(currentMenu);
    }

    /**
     * Attempt to clear the console in a few ways:
     * 1) Print ANSI escape sequences (works in many terminals, including modern Windows 10+ consoles).
     * 2) Fall back to invoking the platform's clear command (cls / clear).
     * 3) Final fallback: print several new lines to visually push previous output up.
     */
    public void clearScreen() {
        // Try ANSI escape first
//        final String ANSI_CLS = "\u001b[2J\u001b[H";
//        try {
//            System.out.print(ANSI_CLS);
//            System.out.flush();
//            // Give the terminal a moment (often not necessary)
//            return;
//        } catch (Throwable ignored) {
//            // ignore and try other options
//        }

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

    public void clearAndRender(String newMenu) {
        setCurrentMenu(newMenu);
        clearScreen();
        render();
    }
}
