package com.command;

import com.jtodo.command.ICommand;
import com.jtodo.toDoObjects.IMainList;
import com.jtodo.toDoObjects.IToDoObject;
import com.jtodo.toDoObjects.MainList;
import com.jtodo.view.IViewController;
import com.jtodo.view.ViewController;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static com.jtodo.command.utils.CommandUtils.*;

public class AllCommandTest {
    private static final IViewController controller = new ViewController();
    private static final Map<String, ICommand> commands = getCommands(controller);

    @Test(expected = Exception.class)
    public void createList() throws Exception {
        controller.addToViewer(new MainList());
        String[] commandLine = {"create", "list", "Liston"};
        commands.get(CREATE_COMMAND).execute(commandLine);
        IToDoObject obj = controller.getLast();
        Assert.assertTrue(obj instanceof IMainList);
        Assert.assertEquals(1, ((IMainList) obj).getLists().size());
        String[] commandLineError = {"create", "list", "Liston", "List"};
        commands.get(CREATE_COMMAND).execute(commandLineError);
        throw new AssertionError("Error: execute command with invalid argument");
    }
}
