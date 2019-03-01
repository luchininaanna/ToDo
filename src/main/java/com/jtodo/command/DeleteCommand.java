package com.jtodo.command;

import com.jtodo.view.IViewController;
import static com.jtodo.command.utils.CommandUtils.*;

public class DeleteCommand implements ICommand {
    private final IViewController viewer;

    public DeleteCommand(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) throws Exception {
        switch (args[1]) {
            case LIST:
                viewer.getLast().deleteList(Integer.parseInt(args[2]));
                break;
            case DEAL:
                viewer.getLast().deleteDeal(Integer.parseInt(args[2]));
                break;
            default:
                throw new Exception(INVALID_COMMAND_ERROR_MSG + DELETE_USAGE_EXAMPLE);
        }
    }
}
