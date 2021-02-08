package edu.epam.project.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {

    public static final Logger logger = LogManager.getLogger(CommandProvider.class);

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current = Optional.empty();
        if (commandName == null) {
            return current;
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, e);
        }
        return current;
    }
}
