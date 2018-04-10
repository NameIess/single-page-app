package com.training.epam.service.impl.template;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public abstract class AbstractTemplateCacheManager {
    private static final Logger Log = LogManager.getLogger(AbstractTemplateCacheManager.class.getSimpleName());

    protected abstract Composite execute(Composite composite, JsonDto jsonDto);

    public List<Composite> handleCriteria(List<Composite> cashedComponent, JsonDto jsonDto) {
        List<Composite> compositeList = new ArrayList<>();

        Set<Composite> iteratedNodes = new LinkedHashSet<>();

        for (Composite composite : cashedComponent) {
            Iterator<Composite> iterator = composite.createIterator();
            while (iterator.hasNext()) {
                Composite node = iterator.next();
                if (!iteratedNodes.contains(node)) {
                    iteratedNodes.add(node);

                    Composite result = execute(node, jsonDto);

                    if (result != null) {
                        compositeList.add(result);
                    }
                }
            }
        }

        return compositeList;
    }
}
