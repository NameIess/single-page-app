package com.training.epam.service;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.service.exception.ServiceException;

import java.util.List;

public interface SkillService {

    List<Composite> findAll() throws ServiceException;

    boolean updateName(JsonDto jsonDto) throws ServiceException;

    List<Composite> searchByCriteria(JsonDto jsonDto) throws ServiceException;

    boolean save(JsonDto jsonDto) throws ServiceException;

    boolean delete(JsonDto jsonDto) throws ServiceException;
}
