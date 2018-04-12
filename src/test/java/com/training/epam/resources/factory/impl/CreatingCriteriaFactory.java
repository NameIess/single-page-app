package com.training.epam.resources.factory.impl;

import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.resources.factory.JsonDtoFactory;

public class CreatingCriteriaFactory implements JsonDtoFactory<CreatingCriteria> {

    @Override
    public CreatingCriteria create(String name) {
        CreatingCriteria criteria = new CreatingCriteria();
        criteria.setParentName(name);
        return criteria;
    }

    @Override
    public CreatingCriteria create() {
        CreatingCriteria criteria = new CreatingCriteria();
        return criteria;
    }
}
