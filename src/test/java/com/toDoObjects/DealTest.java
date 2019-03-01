package com.toDoObjects;

import com.jtodo.toDoObjects.*;
import com.jtodo.status.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DealTest {
    private Deal deal;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        deal  = new Deal("Main deal");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }


    @Test
    public void canCreateUndefinedDeal() {
        Deal undefinedDeal = new Deal();
        assertEquals(undefinedDeal.getName(), "Undefined");
        assertEquals(undefinedDeal.getStatus().toString(), new InProcess().toString());
    }

    @Test
    public void cantPerformToDoListFunctions() {
        assertNull(deal.openList(1));
    }

    @Test
    public void cantCreateList() throws Exception {
        try {
            deal.createList("name");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to create this list. (You are in another list)", error);
        }
    }

    @Test
    public void cantCreateDealInsideThem() throws Exception {
        try {
            deal.createDeal("Deal");
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to create deal. (You are in another list)", error);
        }
    }

    @Test
    public void cantDeleteDeal() throws Exception {
        try {
            deal.deleteDeal(1);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to delete deal. (You are in another list)", error);
        }
    }

    @Test
    public void cantDeleteList() throws Exception {
        try {
            deal.deleteList(4);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("Sorry, at the moment you are unable to delete this list. (You are in another list)", error);
        }
    }

    @Test
    public void canRenameDeal() {
        deal.setName("New name");
        assertEquals(deal.getName(), "New name");
    }

    @Test
    public void isNotEqualsOtherObject()
    {
        Object str = "String isn't equals Deal";
        Object list = new ToDoList("List");

        assertTrue(!deal.equals(str) || !deal.equals(list));
    }

    @Test
    public void canToString() {
        String excepted = "Task1: In process";

        Deal task = new Deal("Task1");
        String dealToString = task.toString();

        assertEquals(excepted, dealToString);
    }

    @Test
    public void canSetName() {
        Deal deal = new Deal();
        String currName = "Task True";

        deal.setName(currName);
        assertEquals(deal.getName(), currName);
    }

    @Test
    public void changeStatus() {
        Deal deal = new Deal();

        IStatus CompletedStatus = new Completed();

        assertNotEquals(deal.getStatus(), CompletedStatus);
        deal.setStatus(CompletedStatus);
        assertEquals(deal.getStatus(), CompletedStatus);
    }
}