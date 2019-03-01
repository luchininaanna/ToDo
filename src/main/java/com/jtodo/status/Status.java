package com.jtodo.status;

public class Status implements IStatus {
    private final String name;

    public Status(String statusName) {
        name = statusName;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof IStatus) {
            IStatus rhs = (IStatus)object;
            return toString().equals(rhs.toString());
        }
        return false;
    }
}
