package com.jtodo.view;

import com.jtodo.toDoObjects.IToDoObject;

public interface IViewController {
    void addToViewer(IToDoObject newObj);

    void deleteLastView();

    IToDoObject getLast();

    void display();

    boolean empty();

}
