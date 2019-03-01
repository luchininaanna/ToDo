package com.command;

import com.jtodo.command.DeleteCommand;
import com.jtodo.command.ICommand;
import com.jtodo.toDoObjects.*;
import com.jtodo.view.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteCommandTest {
    private final MainList mainList = new MainList();
    private final IViewController viewController = new ViewController();
    private final ICommand command = new DeleteCommand(viewController);

    @Test(expected = Exception.class)
    public void failsDeleteNotExistsList() throws Exception {
        viewController.addToViewer(mainList);

        String[] data = { "delete", "list", "1" };
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test(expected = Exception.class)
    public void failsDeleteNotExistsDeal() throws Exception {
        viewController.addToViewer(mainList);

        String[] data = { "delete", "deal", "1" };
        assertEquals(0, mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteExistsList() throws Exception {
        viewController.addToViewer(mainList);
        mainList.addList(new ToDoList("Task1"));
        mainList.addList(new ToDoList("Task3"));
        mainList.addList(new ToDoList("Task2"));


        String[] data = { "delete", "list", "1" };
        assertTrue(0 != mainList.getLists().size());
        command.execute(data);
    }

    @Test
    public void successDeleteExists() throws Exception {
        viewController.addToViewer(mainList);
        mainList.addList(new ToDoList("Task1"));
        mainList.addList(new ToDoList("Task2"));

        String[] data = { "delete", "list", "2" };
        int sizeBefore = mainList.getLists().size();
        command.execute(data);
        int sizeAfter = mainList.getLists().size();
        assertNotEquals(sizeBefore, sizeAfter);
    }

    @Test(expected = Exception.class)
    public void failsDeleteExistsDeal() throws Exception {
        ToDoList list = new ToDoList();
        viewController.addToViewer(list);
        list.addDeal(new Deal("Task1"));
        list.addDeal(new Deal("Task3"));
        list.addDeal(new Deal("Task2"));


        String[] data = { "delete", "list", "1" };
        assertTrue(0 != list.getDeals().size());
        command.execute(data);
    }


}
