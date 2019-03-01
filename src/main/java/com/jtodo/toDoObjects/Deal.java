package com.jtodo.toDoObjects;

import com.jtodo.status.IStatus;
import com.jtodo.status.InProcess;

public class Deal implements IDeal {

    private String dealName;
    private IStatus currStatus;

    //конструкторы
    public Deal() {
        dealName = "Undefined";
        currStatus = new InProcess();
    }

    public Deal(String dealName) {
        this.dealName = dealName;
        this.currStatus = new InProcess();
    }

    public Deal(String dealName, IStatus currStatus) {
        this.dealName = dealName;
        this.currStatus = currStatus;
    }


    //тест есть
    @Override
    public IToDoObject openList(int otherListNumber) {
        System.out.println("Sorry, at the moment you are unable to open this list. (You are in another list)");
        return null;
    }


    //исключения (все покрыты тестами)
    @Override
    public void createList(String listName) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to create this list. (You are in another list)");
    }

    @Override
    public void deleteList(int listNum) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to delete this list. (You are in another list)");
    }

    @Override
    public void createDeal(String dealName) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to create deal. (You are in another list)");
    }

    @Override
    public void deleteDeal(int dealNum) throws Exception {
        throw new Exception("Sorry, at the moment you are unable to delete deal. (You are in another list)");
    }


    //меняем данные по данному делу
    @Override
    public void renameTo(int num, String newName) {
        //setName(newName);
    }

    @Override
    public void changeStatus(int dealNum, IStatus newStatus) {
        //setStatus(newStatus);
    }


    //получаем данные по данному делу
    @Override
    public String getName() {
        return dealName;
    }

    @Override
    public IStatus getStatus() {
        return currStatus;
    }

    @Override
    public String toString() {
        return dealName + ": " + currStatus.toString();
    }


    //устанавливаем параметры
    @Override
    public void setName(String dealName) {
        this.dealName = dealName;
    }

    //может быть @Override???
    public void setStatus(IStatus currStatus) {
        this.currStatus = currStatus;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IDeal) {
            Deal deal = (Deal) obj;
            return deal.getName().equals(this.getName());
        }

        return false;
    }
}