package com.training.epam.factory;

import com.training.epam.entity.Composite;
import org.springframework.stereotype.Component;

@Component("compositeFactoryImpl")
public class CompositeFactoryImpl implements CompositeFactory {

    @Override
    public Composite addChildAndGet(Composite parent, String childName) {
        Composite child = new Composite();
        child.setName(childName);
        parent.addComponent(child);
        return child;
    }

    @Override
    public Composite create() {
        Composite composite = new Composite();
        return composite;
    }
}
