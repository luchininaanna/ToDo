package com.jtodo.command;

import com.jtodo.toDoObjects.IToDoObject;
import com.jtodo.view.IViewController;

import static com.jtodo.command.utils.CommandUtils.*;

public class OpenCommand implements ICommand {
    private final IViewController viewer;

    public OpenCommand(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length != OPEN_COMMAND_SIZE) {
            throw new Exception(ARGS_COUNT_ERROR_MSG + OPEN_USAGE_EXAMPLE);
        }

        int num = Integer.parseInt(args[1]);

        IToDoObject obj = viewer.getLast().openList(num);
        if (obj != null) {
            viewer.addToViewer(obj);
        } else {
            throw new Exception(INVALID_COMMAND_ERROR_MSG);
        }
    }
}
