package com.training.epam.entity;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<Composite> {
    private Stack<Iterator<Composite>> stack = new Stack<>();

    public CompositeIterator(Iterator<Composite> iterator) {
        stack.push(iterator);
    }

    @Override
    public Composite next() {
        Composite composite = null;
        if (hasNext()) {
            Iterator<Composite> iterator = stack.peek();
            composite = iterator.next();

            Iterator<Composite> compositeIterator = composite.createIterator();
            stack.push(compositeIterator);
        }

        return composite;
    }

    @Override
    public boolean hasNext() {
        boolean hasNextIterator;
        if (!stack.empty()) {
            Iterator<Composite> iterator = stack.peek();

            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();           // RECURSION

            } else {
                hasNextIterator = true;
            }
        } else {
            hasNextIterator = false;
        }

        return hasNextIterator;
    }
}
