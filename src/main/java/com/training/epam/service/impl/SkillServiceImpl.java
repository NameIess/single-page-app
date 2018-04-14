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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service("skillService")
public class SkillServiceImpl implements SkillService {
    private List<Composite> cashedComponent;
    private SkillRepository skillRepository;
    private AbstractTemplateCacheManager cacheManager;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private AtomicBoolean isActualData = new AtomicBoolean(false);

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, AbstractTemplateCacheManager cacheManager) {
        this.skillRepository = skillRepository;
        this.cacheManager = cacheManager;
    }


    @Override
    public List<Composite> findAll() throws ServiceException {
        try {
            readLock.lock();

            if (!isActualData.get()) {
                cashedComponent = skillRepository.findAll();
                checkReceivedData(cashedComponent);
                isActualData.set(true);
            }

            return cashedComponent;
        } catch (DaoException e) {
            throw new ServiceException("Exception within findAll(): " + e.getMessage(), e);
        }
        finally {
            readLock.unlock();
        }
    }


    @Override
    public boolean updateName(JsonDto jsonDto) throws ServiceException {
        try {
            writeLock.lock();

            boolean isUpdated = skillRepository.update(jsonDto);
            if (isUpdated) {
                isActualData.set(false);
            }

            return isUpdated;
        } catch (DaoException e) {
            throw new ServiceException("Exception within updateNode(): " + e.getMessage(), e);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Composite> searchByCriteria(JsonDto jsonDto) throws ServiceException {
        try {
            readLock.lock();

        findAll();
            List<Composite> compositeList;

            if (!isActualData.get()) {
                cashedComponent = findAll();
            }
            compositeList = cacheManager.handleCriteria(cashedComponent, jsonDto);
            checkReceivedData(compositeList);

            return compositeList;
        }
        finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean save(JsonDto jsonDto) throws ServiceException {
        try {
            writeLock.lock();

            boolean isSaved = skillRepository.save(jsonDto);
            if (isSaved) {
                isActualData.set(false);
            }

            return isSaved;
        } catch (DaoException e) {
            throw new ServiceException("Exception within save(): " + e.getMessage(), e);
        }
        finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean delete(JsonDto jsonDto) throws ServiceException {
        try {
            writeLock.lock();

            boolean isDeleted = skillRepository.delete(jsonDto);
            if (isDeleted) {
                isActualData.set(false);
            }

            return isDeleted;
        } catch (DaoException e) {
            throw new ServiceException("Exception within delete(): " + e.getMessage(), e);
        }
        finally {
            writeLock.unlock();
        }
    }

    private void checkReceivedData(List<Composite> compositeList) throws ServiceException {
        if (compositeList == null || compositeList.isEmpty()) {
            throw new ServiceException("Data not found");
        }
    }
}
