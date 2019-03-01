package com.jtodo.view;

import com.jtodo.toDoObjects.IToDoObject;

import java.util.Stack;

public class ViewController implements IViewController {
    private final Stack<IToDoObject> StackOfObjects = new Stack<>();

    @Override
    public boolean empty() {
        return this.StackOfObjects.empty();
    }

    @Override
    public IToDoObject getLast() {
        return this.StackOfObjects.peek();
    }

    @Override
    public void display() {
        if (this.StackOfObjects.size() > 0)
            System.out.println(this.StackOfObjects.peek());
    }

    @Override
    public void addToViewer(IToDoObject newObject) {
        this.StackOfObjects.add(newObject);
    }

    @Override
    public void deleteLastView() {
        this.StackOfObjects.pop();
    }
}
