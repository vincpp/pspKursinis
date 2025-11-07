package com.psp;

import com.psp.utils.*;

import java.nio.file.Path;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

    GameEngine gameEngine = new GameEngine();
    TurnManager turnManager = new TurnManager();

    Config config = Config.loadFromFile("src/main/java/com/psp/config.json");
    Logger logger = new Logger(Path.of(config.getLogFile()), config.getLogLevel());

    logger.logMessage("Game started.");
    logger.logMessage("Log file at: " + config.getLogFile());

    RandomProvider rng = new RandomProvider(config.getSeed());


    gameEngine.start();

    }
}