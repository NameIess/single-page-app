package resources.factory.impl;

import com.training.epam.entity.dto.request.UpdatingCriteria;
import resources.factory.JsonDtoFactory;

public class UpdatingCriteriaFactory implements JsonDtoFactory<UpdatingCriteria> {

    @Override
    public UpdatingCriteria create(String id) {
        UpdatingCriteria criteria = new UpdatingCriteria();
        criteria.setCurrentName(id);
        return criteria;
    }

    @Override
    public UpdatingCriteria create() {
        UpdatingCriteria criteria = new UpdatingCriteria();
        return criteria;
    }

    @Override
    public UpdatingCriteria create(String id, String target) {
        UpdatingCriteria criteria = new UpdatingCriteria();
        criteria.setCurrentName(id);
        criteria.setUpdatedName(target);
        return criteria;
    }
}
