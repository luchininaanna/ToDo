package com.jtodo.toDoObjects;

import java.util.List;

public interface IToDoList extends IToDoObject {
    List<IDeal> getDeals();

    void addDeal(IDeal newDeal);
}
