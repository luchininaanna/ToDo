package com.jtodo.command;

import com.jtodo.status.IStatus;
import com.jtodo.view.IViewController;

import static com.jtodo.command.utils.CommandUtils.*;

public class ChangeCommand implements ICommand {
    private final IViewController viewer;

    public ChangeCommand(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == CHANGE_COMMAND_SIZE && args[1].equals(STATUS)) {
            IStatus status = defineStatus(args[3]);
            if (status != null) {
                viewer.getLast().changeStatus(Integer.parseInt(args[2]), status);
            } else {
                throw new Exception(UNKNOWN_STATUS_ERROR_MSG + STATUS_USAGE_EXAMPLE);
            }
        } else {
            throw new Exception(ARGS_COUNT_ERROR_MSG + CHANGE_STATUS_USAGE_EXAMPLE);
        }
    }
}
