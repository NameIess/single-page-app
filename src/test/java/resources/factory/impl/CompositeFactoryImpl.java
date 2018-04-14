package resources.factory.impl;

import com.training.epam.entity.Composite;
import resources.factory.CompositeFactory;

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
