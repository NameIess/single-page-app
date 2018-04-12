package com.training.epam.resources.factory.impl;

import com.training.epam.entity.Composite;
import com.training.epam.resources.factory.CompositeFactory;

public class CompositeFactoryImpl implements CompositeFactory {
    @Override
    public Composite create() {
        Composite composite = new Composite();
        return composite;
    }

    @Override
    public Composite create(String name) {
        Composite composite = new Composite();
        composite.setName(name);
        return composite;
    }
}
