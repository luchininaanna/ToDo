package com.toDoObjects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.jtodo.toDoObjects.*;
import com.jtodo.status.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainListTest {
    private MainList mainList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        mainList = new MainList();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void canConstructedFromOtherList() {
        List<IToDoList> testList = new ArrayList<>();
        MainList list = new MainList(testList);
        assertEquals(list.getLists(), testList);
    }

    @Test
    public void canCreateToDoList() {
        mainList.createList("New list");
        assertEquals(mainList.getLists().get(0).getName(), "New list");
    }

    @Test
    public void canRenameList(){
        mainList.createList("Other list");
        mainList.renameTo(1, "New list");
        assertEquals(mainList.getLists().get(0).getName(), "New list");
    }

    @Test
    public void canOpenList() {
        mainList.createList("New list");
        ToDoList toDoList = new ToDoList("New list");
        assertEquals(mainList.openList(1), toDoList);
    }

    @Test
    public void cantOpenNonexistentList() {
        assertNull(mainList.openList(1));
    }

    @Test
    public void canDeleteList() throws Exception {
        mainList.createList("List");
        mainList.deleteList(1);
    }

    @Test
    public void cantCreateDeal() throws Exception {

        try {
            mainList.createDeal("deal");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't create deal here.", error);
        }
    }

    @Test
    public void cantDeleteDeal() throws Exception {
        try {
            mainList.deleteDeal(1);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't delete deal here.", error);
        }
    }


    @Test
    public void cantChangeDealStatus() throws Exception {
        IStatus status = new Status("Completed");
        try {
            mainList.changeStatus(1,status);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, but you can't change status here.", error);
        }
    }

    @Test
    public void cantGetMainListName() {
        assertNull(mainList.getName());
    }

    @Test
    public void cantGetStatus() {
        assertNull(mainList.getStatus());
    }

    @Test
    public void canPrintMainListToString() {
        mainList.createList("Other list");
        System.out.println(mainList.toString());

    }
    @Test
    public void canPrintEmptyList() {
        System.out.println(mainList.toString());
    }
}