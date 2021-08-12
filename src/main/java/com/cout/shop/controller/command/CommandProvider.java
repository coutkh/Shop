package com.cout.shop.controller.command;


import com.cout.shop.controller.command.impl.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private CommandProvider(){}

    public static Command getCommand(String commandName){
        Command currentCommand;

        if (commandName != null){
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = commandType.getCommand();
            } catch (IllegalArgumentException e){
                logger.error("No such command found {}", commandName, e);
                currentCommand = new UnknownCommand();
            }
        } else {
            currentCommand = new UnknownCommand();
        }
        return currentCommand;
    }
}
