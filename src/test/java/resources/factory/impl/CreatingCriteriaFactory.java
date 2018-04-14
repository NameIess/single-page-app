package resources.factory.impl;

import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.SearchingCriteria;
import resources.factory.JsonDtoFactory;

public class CreatingCriteriaFactory implements JsonDtoFactory<CreatingCriteria> {

    @Override
    public CreatingCriteria create(String id) {
        CreatingCriteria criteria = new CreatingCriteria();
        criteria.setParentName(id);
        return criteria;
    }

    @Override
    public CreatingCriteria create() {
        CreatingCriteria criteria = new CreatingCriteria();
        return criteria;
    }

    @Override
    public CreatingCriteria create(String id, String target) {
        CreatingCriteria criteria = new CreatingCriteria();
        criteria.setParentName(id);
        criteria.setChildName(target);
        return criteria;
    }
}
