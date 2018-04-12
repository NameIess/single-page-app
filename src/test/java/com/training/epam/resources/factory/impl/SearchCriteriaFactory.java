package com.training.epam.resources.factory.impl;

import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.resources.factory.JsonDtoFactory;

public class SearchCriteriaFactory implements JsonDtoFactory<SearchingCriteria> {

    @Override
    public SearchingCriteria create(String name) {
        SearchingCriteria criteria = new SearchingCriteria();
        criteria.setCurrentName(name);
        return criteria;
    }

    @Override
    public SearchingCriteria create() {
        SearchingCriteria criteria = new SearchingCriteria();
        return criteria;
    }
}
