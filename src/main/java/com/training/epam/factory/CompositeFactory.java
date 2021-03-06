package com.training.epam.factory;

import com.training.epam.entity.Composite;

public interface CompositeFactory {
    Composite create();

    Composite addChildAndGet(Composite parent, String childName);
}
