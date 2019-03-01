package com.jtodo.toDoObjects;

import com.jtodo.status.IStatus;

public interface IDeal extends IToDoObject {
    String toString();

    String getName();
    void setStatus(IStatus status);
}