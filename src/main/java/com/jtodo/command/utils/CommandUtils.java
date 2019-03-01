package com.jtodo.command.utils;

import com.jtodo.command.*;
import com.jtodo.status.Completed;
import com.jtodo.status.IStatus;
import com.jtodo.status.InProcess;
import com.jtodo.view.IViewController;

import java.util.HashMap;
import java.util.Map;

public class CommandUtils {
    public static final int MinCommands = 1;
    public static final int MIN_CREATE_COMMAND_SIZE = 3;
    public static final int MIN_RENAME_COMMAND_SIZE = 3;
    public static final int CHANGE_COMMAND_SIZE = 4;
    public static final int OPEN_COMMAND_SIZE = 2;

    public static final String CREATE_COMMAND = "create";
    private static final String OPEN_COMMAND = "open";
    private static final String DELETE_COMMAND = "delete";
    private static final String RENAME_COMMAND = "rename";
    private static final String CHANGE_COMMAND = "change";
    private static final String EXIT_COMMAND = "exit";

    public static final String LIST = "list";
    public static final String DEAL = "deal";
    public static final String ALL = "all";
    public static final String STATUS = "status";
    private static final String STATUS_IN_PROCESS = "process";
    private static final String STATUS_COMPLETED = "completed";

    public static final String ARGS_COUNT_ERROR_MSG = "Invalid args count.\n";
    public static final String INVALID_COMMAND_ERROR_MSG = "Invalid command.\n";
    public static final String UNKNOWN_STATUS_ERROR_MSG = "Unknown status.\n";
    public static final String UnknownCommand = "Unknown command.\n";

    public static final String CREATE_USAGE_EXAMPLE = "Use: create <list|deal> <name>.\n";
    public static final String OPEN_USAGE_EXAMPLE = "Use: open <number>.\n";
    public static final String DELETE_USAGE_EXAMPLE = "Use: delete <list|deal> <number>.\n";
    public static final String RENAME_USAGE_EXAMPLE = "Use: rename <number> <new name>.\n";
    public static final String STATUS_USAGE_EXAMPLE = "Use: \"in process\" or \"completed\".\n";
    public static final String CHANGE_STATUS_USAGE_EXAMPLE = "Use: change status <number> <" + STATUS_IN_PROCESS + "|" + STATUS_COMPLETED + ">.\n";

    public static final String UncorrectInput = "You need input number ";

    public static String findCommandArgs(String[] arr, int start, int end) {
        StringBuilder res = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(arr[i]);
        }
        return res.toString();
    }

    public static IStatus defineStatus(String statusStr) {
        IStatus status = null;
        switch (statusStr) {
            case STATUS_IN_PROCESS:
                status = new InProcess();
                break;
            case STATUS_COMPLETED:
                status = new Completed();
                break;
        }

        return status;
    }

    public static Map<String, ICommand> getCommands(IViewController viewer) {
        Map<String, ICommand> commands = new HashMap<>();
        commands.put(OPEN_COMMAND, new OpenCommand(viewer));
        commands.put(CREATE_COMMAND, new CreateCommand(viewer));
        commands.put(CHANGE_COMMAND, new ChangeCommand(viewer));
        commands.put(DELETE_COMMAND, new DeleteCommand(viewer));
        commands.put(RENAME_COMMAND, new RenameCommand(viewer));
        commands.put(EXIT_COMMAND, new ExitCommand(viewer));
        return commands;
    }
}
