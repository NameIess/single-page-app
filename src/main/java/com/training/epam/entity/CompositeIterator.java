package com.training.epam.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class CompositeIterator implements Iterator<Composite> {
    private Stack<Iterator<Composite>> stack = new Stack<>();
    private Set<Composite> iteratedComposite = new HashSet<>();

    public CompositeIterator(Iterator<Composite> iterator) {
        stack.push(iterator);
    }

    @Override
    public Composite next() {
        Composite composite = null;
        if (hasNext()) {
            Iterator<Composite> iterator = stack.peek();
            composite = iterator.next();
            if (!iteratedComposite.contains(composite)) {
                Iterator<Composite> compositeIterator = composite.createIterator();
                stack.push(compositeIterator);
                iteratedComposite.add(composite);
            }
        }

        return composite;
    }

    @Override
    public boolean hasNext() {
        boolean hasMoreIterators;
        if (!stack.empty()) {
            Iterator<Composite> iterator = stack.peek();

            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();           // RECURSION

            } else {
                hasMoreIterators = true;
            }
        } else {
            hasMoreIterators = false;
        }

        return hasMoreIterators;
    }
}
