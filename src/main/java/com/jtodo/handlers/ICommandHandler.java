package com.jtodo.handlers;

import com.jtodo.view.IViewController;

public interface ICommandHandler {
    boolean handleCommand(String command, IViewController viewer);
}
