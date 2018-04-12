package com.training.epam.service.impl;

import com.training.epam.dao.SkillRepository;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import com.training.epam.service.impl.template.AbstractTemplateCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("skillService")
public class SkillServiceImpl implements SkillService {
    private List<Composite> cashedComponent = new CopyOnWriteArrayList<>();
    private SkillRepository skillRepository;
    private AbstractTemplateCacheManager cacheManager;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, AbstractTemplateCacheManager cacheManager) {
        this.skillRepository = skillRepository;
        this.cacheManager = cacheManager;
    }


    @Override
    public List<Composite> findAll() throws ServiceException {
        try {
            cashedComponent = skillRepository.findAll();
            checkReceivedData(cashedComponent);

            return cashedComponent;
        } catch (DaoException e) {
            throw new ServiceException("Exception within findAll(): " + e.getMessage(), e);
        }
    }


    @Override
    public boolean updateName(JsonDto jsonDto) throws ServiceException {
        try {
            boolean isUpdated = skillRepository.update(jsonDto);
            return isUpdated;

        } catch (DaoException e) {
            throw new ServiceException("Exception within updateName(): " + e.getMessage(), e);
        }
    }

    @Override
    public List<Composite> searchByCriteria(JsonDto jsonDto) throws ServiceException {
        List<Composite> compositeList = cacheManager.handleCriteria(cashedComponent, jsonDto);

        checkReceivedData(compositeList);

        return compositeList;
    }

    @Override
    public boolean save(JsonDto jsonDto) throws ServiceException {
        try {
            boolean isSaved = skillRepository.save(jsonDto);
            return isSaved;

        } catch (DaoException e) {
            throw new ServiceException("Exception within save(): " + e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(JsonDto jsonDto) throws ServiceException {
        try {
            boolean isDeleted = skillRepository.delete(jsonDto);
            return isDeleted;

        } catch (DaoException e) {
            throw new ServiceException("Exception within delete(): " + e.getMessage(), e);
        }
    }

    private void checkReceivedData(List<Composite> compositeList) throws ServiceException {
        if (compositeList == null || compositeList.isEmpty()) {
            throw new ServiceException("Data not found");
        }
    }
}
