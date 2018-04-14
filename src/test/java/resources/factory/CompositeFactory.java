package resources.factory;

import com.training.epam.entity.Composite;

public interface CompositeFactory {
    Composite create();

    Composite create(String name);
}
