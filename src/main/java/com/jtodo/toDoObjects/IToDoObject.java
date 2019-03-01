package com.jtodo.toDoObjects;

import com.jtodo.status.IStatus;

public interface IToDoObject {
    IToDoObject openList(int index);

    void setName(String nameOfList);

    String getName();

    void changeStatus(int dealIndex, IStatus newStatus) throws Exception;

    IStatus getStatus();

    void createList(String listName) throws Exception;

    void deleteList(int listIndex) throws Exception;

    void createDeal(String dealName) throws Exception;

    void deleteDeal(int dealIndex) throws Exception;

    void renameTo(int index, String newName);

    String toString();
}
