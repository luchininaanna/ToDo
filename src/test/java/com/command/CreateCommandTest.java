package com.command;

import com.jtodo.command.CreateCommand;
import com.jtodo.toDoObjects.*;
import com.jtodo.view.IViewController;
import com.jtodo.view.ViewController;
import org.junit.Test;

public class CreateCommandTest {
    private final IToDoObject mainList = new MainList();
    private final IViewController viewController = new ViewController();

    @Test(expected = Exception.class)
    public void failsWithInsufficientArguments() throws Exception {
        viewController.addToViewer(mainList);
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "list" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsWithExcessiveArguments() throws Exception {
        viewController.addToViewer(mainList);
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "list", "list", "boom" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateDealWhenPointerIntoMainList() throws Exception {
        viewController.addToViewer(mainList);
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "deal", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateUnknownEntity() throws Exception {
        viewController.addToViewer(mainList);
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "boom", "Boom" };
        command.execute(data);
    }

    @Test
    public void successCreateDealWhenPointerINtoToDoList() throws Exception {
        viewController.addToViewer(new ToDoList());
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "deal", "Task1" };
        command.execute(data);
    }

    @Test
    public void successCreateListWhenPointerInMainList() throws Exception {
        viewController.addToViewer(new MainList());
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateListWhenPointerINtoToDoList() throws Exception {
        viewController.addToViewer(new ToDoList());
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateDealWhenPointerIntoDeal() throws Exception {
        viewController.addToViewer(new Deal());
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "deal", "Task1" };
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsCreateListWhenPointerIntoDeal() throws Exception {
        viewController.addToViewer(new Deal());
        CreateCommand command = new CreateCommand(viewController);

        String[] data = { "create", "list", "Task1" };
        command.execute(data);
    }
}
