package com.toDoObjects;

import com.jtodo.status.*;
import com.jtodo.toDoObjects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ToDoListTest {
    private IToDoList list;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void initialization() {
        list = new ToDoList("Test");
        Deal deal = new Deal("Deal");
        list.addDeal(deal);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanStream() {
        System.setOut(null);
    }

    @Test
    public void TestMethodToString(){
        ToDoList toDoList = new ToDoList();
        assertEquals(toDoList.toString(), "Undefined\nyour todo list is empty.\nBut you can create new deals.");
        IDeal deal = new Deal();
        toDoList.addDeal(deal);
        assertEquals(toDoList.toString(), "Undefined\n01. Undefined: In process\n");
    }

    @Test
    public void canCreateUndefinedToDoList() {
        ToDoList toDoList = new ToDoList();
        assertEquals(toDoList.getName(), "Undefined");
        assertEquals(0, toDoList.getDeals().size());
    }

    @Test
    public void canCreateDealInToDoList() throws Exception {
        list.createDeal("deal");
        assertEquals(2, list.getDeals().size());
    }

    @Test
    public void canDeleteDealFromToDoList() throws Exception {
        list.deleteDeal(1);
        assertEquals(list.toString(), list.getName() + "\nyour todo list is empty.\nBut you can create new deals.");
        assertEquals(0, list.getDeals().size());
    }

    @Test
    public void canChangeStatusOfToDoList() throws Exception {
        list.changeStatus(1 , new Completed());
    }

    @Test
    public void cantOpenList() {
        assertNull(list.openList(1));
    }

    @Test
    public void cantCreateListInToDoList() {
        try{
            list.createList("Lestrade");
        }catch (Exception e){
            String error = e.getMessage();
            assertEquals("you can't create list here.", error);
        }
    }

    @Test
    public void cantDeleteListFromToDoList() {
        try{
            list.deleteList(2);
        }catch (Exception e){
            String error = e.getMessage();
            assertEquals("you can't delete list here.", error);
        }
    }

    @Test
    public void getDealsTest(){
        ToDoList toDoList = new ToDoList();
        IDeal deal = new Deal();
        toDoList.addDeal(deal);
        assertEquals(toDoList.getDeals().get(0), deal);
    }

    @Test
    public void canSetToDoListName() {
        list.setName("list");
        assertEquals(list.getName(), "list");
    }
    @Test
    public void cantGetToDoListStatus() {
        assertNull(list.getStatus());
    }
    @Test
    public void canRenameDeal() {
        list.renameTo(1, "new name");
    }
}