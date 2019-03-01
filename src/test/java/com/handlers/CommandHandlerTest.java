package com.handlers;

import com.jtodo.handlers.CommandHandler;
import com.jtodo.handlers.ICommandHandler;
import com.jtodo.toDoObjects.IMainList;
import com.jtodo.toDoObjects.MainList;
import com.jtodo.view.IViewController;
import com.jtodo.view.ViewController;
import org.junit.Before;
import org.junit.Test;

import static com.jtodo.command.utils.CommandUtils.getCommands;
import static org.junit.Assert.assertTrue;

public class CommandHandlerTest {
    private ICommandHandler commandHandler;
    private IViewController viewController;

    @Before
    public void init() {
        IMainList mainList = new MainList();
        viewController = new ViewController();
        commandHandler = new CommandHandler(getCommands(viewController));
        viewController.addToViewer(mainList);

    }

    @Test
    public void testCreateList() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
    }


    @Test
    public void testOpenList() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
    }

    @Test
    public void testCreateDeal() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("create deal new", viewController));
    }

    @Test
    public void testRenameList() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("rename 1 name", viewController));
    }

    @Test
    public void testRenameDeal() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("create deal deal", viewController));
        assertTrue(commandHandler.handleCommand("rename 1 name", viewController));
    }

    @Test
    public void testDeleteDeal() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("create deal deal", viewController));
        assertTrue(commandHandler.handleCommand("delete deal 1", viewController));
    }


    @Test
    public void testDeleteList() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("delete list 1", viewController));
    }

    @Test
    public void testExitDeal() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("exit", viewController));
    }

    @Test
    public void testExitProg() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("exit", viewController));
        assertTrue(commandHandler.handleCommand("exit", viewController));
    }

    @Test
    public void testChange() {
        assertTrue(commandHandler.handleCommand("create list Test", viewController));
        assertTrue(commandHandler.handleCommand("open 1", viewController));
        assertTrue(commandHandler.handleCommand("create deal deal", viewController));
        assertTrue(commandHandler.handleCommand("change status 1 completed", viewController));
        assertTrue(commandHandler.handleCommand("change status 1 process", viewController));
    }


}
