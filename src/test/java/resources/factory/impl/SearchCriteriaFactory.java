package resources.factory.impl;

import com.training.epam.entity.dto.request.SearchingCriteria;
import resources.factory.JsonDtoFactory;

public class SearchCriteriaFactory implements JsonDtoFactory<SearchingCriteria> {

    @Override
    public SearchingCriteria create(String id) {
        SearchingCriteria criteria = new SearchingCriteria();
        criteria.setCurrentName(id);
        return criteria;
    }

    @Override
    public SearchingCriteria create() {
        SearchingCriteria criteria = new SearchingCriteria();
        return criteria;
    }

    @Override
    public SearchingCriteria create(String id, String target) {
        return null;
    }
}
