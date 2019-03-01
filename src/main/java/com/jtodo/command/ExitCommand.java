package com.jtodo.command;

import com.jtodo.view.IViewController;

public class ExitCommand implements ICommand {
    private final IViewController viewer;

    public ExitCommand(IViewController viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(String[] args) {
        viewer.deleteLastView();
    }
}
