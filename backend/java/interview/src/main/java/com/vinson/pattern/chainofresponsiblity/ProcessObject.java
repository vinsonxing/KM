package com.vinson.pattern.chainofresponsiblity;

import java.util.Objects;

public abstract class ProcessObject<T> {

    protected ProcessObject<T> successor;

    public void setSuccessor(ProcessObject<T> s) {
        this.successor = s;
    }

    public T handle(T input) {
        T r =  handleWork(input);
        if (Objects.nonNull(successor)) {
            r = successor.handle(r);
        }

        return r;
    }

    abstract protected T handleWork(T input);
}
