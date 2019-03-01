package com.jtodo.command;

import com.jtodo.view.IViewController;

import static com.jtodo.command.utils.CommandUtils.*;

public class RenameCommand implements ICommand {
    private final IViewController viewer;

    public RenameCommand(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length < MIN_RENAME_COMMAND_SIZE) {
            throw new Exception(ARGS_COUNT_ERROR_MSG + RENAME_USAGE_EXAMPLE);
        }
        viewer.getLast().renameTo(Integer.parseInt(args[1]), findCommandArgs(args, 2, args.length));
    }
}
