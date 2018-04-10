package com.training.epam.service.impl;

import com.training.epam.dao.SkillRepository;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.dao.impl.SkillRepositoryImpl;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import com.training.epam.service.impl.template.AbstractTemplateCacheManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

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

            return cashedComponent;
        } catch (DaoException e) {
            throw new ServiceException("Exception within findAll(): " + e.getMessage(), e);
        }
    }


    @Override
    public void updateName(JsonDto jsonDto) throws ServiceException {
        try {
            skillRepository.update(jsonDto);

        } catch (DaoException e) {
            throw new ServiceException("Exception within updateName(): " + e.getMessage(), e);
        }
    }

    @Override
    public List<Composite> searchByCriteria(JsonDto jsonDto) throws ServiceException {
        List<Composite> compositeList = cacheManager.handleCriteria(cashedComponent, jsonDto);

        if (compositeList == null || compositeList.isEmpty()) {
            throw new ServiceException("Object not fount");
        }

        return compositeList;
    }

    @Override
    public void save(JsonDto jsonDto) throws ServiceException {
        try {
            skillRepository.save(jsonDto);

        } catch (DaoException e) {
            throw new ServiceException("Exception within save(): " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(JsonDto jsonDto) throws ServiceException {
        try {
            skillRepository.delete(jsonDto);

        } catch (DaoException e) {
            throw new ServiceException("Exception within delete(): " + e.getMessage(), e);
        }
    }
}
