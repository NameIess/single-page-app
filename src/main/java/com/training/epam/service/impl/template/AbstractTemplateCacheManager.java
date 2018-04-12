package com.training.epam.service.impl.template;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;

import java.util.*;

public abstract class AbstractTemplateCacheManager {

    protected abstract Composite execute(Composite composite, JsonDto jsonDto);

    public List<Composite> handleCriteria(List<Composite> cashedComponent, JsonDto jsonDto) {
        List<Composite> compositeList = new ArrayList<>();
        Set<Composite> iteratedNodes = new LinkedHashSet<>();

        for (Composite root : cashedComponent) {
            Iterator<Composite> iterator = root.createIterator();
            verifyRootNode(jsonDto, compositeList, root);

            if (iterator.hasNext()) {
                verifyChildNode(jsonDto, compositeList, iteratedNodes, iterator);
            }
        }

        return compositeList;
    }

    private void verifyChildNode(JsonDto jsonDto, List<Composite> compositeList, Set<Composite> iteratedNodes, Iterator<Composite> iterator) {
        while (iterator.hasNext()) {
            Composite child = iterator.next();
            if (!iteratedNodes.contains(child)) {
                iteratedNodes.add(child);
                verifyRootNode(jsonDto, compositeList, child);
            }
        }
    }

    private void verifyRootNode(JsonDto jsonDto, List<Composite> compositeList, Composite node) {
        Composite found = execute(node, jsonDto);

        if (found != null) {
            compositeList.add(found);
        }
    }
}
