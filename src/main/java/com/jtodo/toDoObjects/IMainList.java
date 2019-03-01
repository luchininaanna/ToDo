package com.jtodo.toDoObjects;

import java.util.List;

public interface IMainList extends IToDoObject {
    List<IToDoList> getLists();

    void addList(IToDoList list);
}
