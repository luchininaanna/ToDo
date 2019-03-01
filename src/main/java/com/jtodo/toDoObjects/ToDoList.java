package com.jtodo.toDoObjects;

import com.jtodo.status.IStatus;

import java.util.ArrayList;
import java.util.List;

public class ToDoList implements IToDoList {
    private String nameOfToDoList;
    private final List<IDeal> dealsInList = new ArrayList<>();
    private static final String BLANK_LIST = "your todo list is empty.\nBut you can create new deals.";


    public ToDoList() {
        this.nameOfToDoList = "Undefined";
    }

    public ToDoList(String name) {
        this.nameOfToDoList = name;
    }

    @Override
    public void setName(String nameOfList) {
        this.nameOfToDoList = nameOfList;
    }

    @Override
    public String getName() {
        return this.nameOfToDoList;
    }

    @Override
    public void createList(String listName) throws Exception {
        throw new Exception("you can't create list here.");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        throw new Exception("you can't delete list here.");
    }

    @Override
    public void createDeal(String dealName) {
        IDeal deal = new Deal(dealName);
        this.dealsInList.add(deal);
    }

    @Override
    public void deleteDeal(int indexOfDeal) throws Exception {
        indexOfDeal--;
        if (indexOfDeal >= this.dealsInList.size())
            throw new Exception("Deal isn't exits");
        else
            this.dealsInList.remove(indexOfDeal);
    }

    @Override
    public List<IDeal> getDeals() {
        return this.dealsInList;
    }

    @Override
    public void addDeal(IDeal newDeal) {
        this.dealsInList.add(newDeal);
    }

    @Override
    public void changeStatus(int dealCount, IStatus newStatus) throws Exception {
        if (this.dealsInList.size() <= (--dealCount))
            throw new Exception("Deal isn't exists.");

        IDeal changedDeal = this.dealsInList.get(dealCount);
        changedDeal.setStatus(newStatus);
    }

    @Override
    public IStatus getStatus() {
        return null;
    }

    @Override
    public IToDoObject openList(int num) {
        System.out.println("you can't open list here.");
        return null;
    }


    @Override
    public void renameTo(int indexOfDeal, String name) {
        indexOfDeal--;
        IDeal changedDeal = new Deal(name);
        this.dealsInList.remove(indexOfDeal);
        this.dealsInList.add(indexOfDeal, changedDeal);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(this.nameOfToDoList);
        res.append('\n');
        for (int i = 0; i < this.dealsInList.size(); i++) {
            int j = i + 1;
            res.append(Integer.toString(j) + ". " + this.dealsInList.get(i).getName() + ": " + this.dealsInList.get(i).getStatus() + "\n");
        }
        if (res.toString().equals(this.nameOfToDoList + '\n'))
            res.append(BLANK_LIST);

        return res.toString();
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (anotherObject instanceof ToDoList) {
            ToDoList list = (ToDoList) anotherObject;
            return (list.getName().equals(this.getName())) && (list.getDeals().equals(this.getDeals()));
        }

        return false;
    }
}
