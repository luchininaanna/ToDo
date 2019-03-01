package com.jtodo.toDoObjects;

import com.jtodo.status.IStatus;

import java.util.ArrayList;
import java.util.List;

public class MainList implements IMainList {
    private static final String EMPTY_MSG = "Sorry, but your todo lists are missing.\nDon't worry, you can create them right now!";
    private List<IToDoList> lists;

    public MainList() {
        lists = new ArrayList<>();
    }

    public MainList(List<IToDoList> lists) {
        this.lists = new ArrayList<>();
        this.lists = lists;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        int i = 0;
        while(i < lists.size()){
            int j = i + 1;
            res.append(j);
            res.append(". ");
            res.append(lists.get(i).getName());
            res.append('\n');
            i++;
        }
        if (res.toString().equals("")) {
            res = new StringBuilder(EMPTY_MSG);
        }
        return res.toString();
    }

    @Override
    public IToDoObject openList(int num) {
        int numOfList = num - 1;
        if (numOfList < lists.size() && numOfList >= 0) {
            return lists.get(numOfList);
        }
        return null;
    }

    @Override
    public void createList(String listName) {
        lists.add(new ToDoList(listName));
    }

    @Override
    public void createDeal(String dealName) throws Exception {
        throw new Exception("Sorry, but you can't create deal here.");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        int numOfList = listNum - 1;
        if (numOfList >= lists.size()) {
            throw new Exception("List isn't exists.");
        }
        lists.remove(numOfList);
    }

    @Override
    public void deleteDeal(int dealNum) throws Exception {
        throw new Exception("Sorry, but you can't delete deal here.");
    }

    @Override
    public void renameTo(int num, String newName) {
        int numOfList = num - 1;
        if ( numOfList < lists.size()) {
            lists.get(numOfList).setName(newName);
        }
    }

    @Override
    public void changeStatus(int dealNum, IStatus newStatus) throws Exception {
        throw new Exception("Sorry, but you can't change status here.");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public IStatus getStatus() {
        return null;
    }

    @Override
    public List<IToDoList> getLists() {
        return lists;
    }

    @Override
    public void addList(IToDoList list) {
        lists.add(list);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MainList) {
            MainList list = (MainList) obj;
            return list.getLists().equals(this.getLists());
        }
        return false;
    }
}