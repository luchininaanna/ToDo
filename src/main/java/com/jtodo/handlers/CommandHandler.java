package com.jtodo.handlers;

import com.jtodo.command.ICommand;
import com.jtodo.view.IViewController;

import java.util.Map;

import static com.jtodo.command.utils.CommandUtils.*;

public class CommandHandler implements ICommandHandler {
    private final Map<String, ICommand> commands;

    //constructor
    public CommandHandler(Map<String, ICommand> commands) {
        this.commands = commands;
    }

    @Override
    public boolean handleCommand(String commandLine, IViewController viewer) {
        boolean result = true;
        String[] arguments = commandLine.split(" ");
        try {
            if (arguments.length < MinCommands || !commands.containsKey(arguments[0])) {
                throw new Exception(UnknownCommand);
            }

            ICommand command = commands.get(arguments[0]);
            command.execute(arguments);
        } catch (NumberFormatException ex) {
            System.out.println(UncorrectInput + ex.getMessage());
            result = false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = false;
        }
        return result;
    }
}