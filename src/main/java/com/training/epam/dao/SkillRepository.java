package com.training.epam.dao;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;

import java.util.List;

public interface SkillRepository {

    List<Composite> findAll() throws DaoException;

    void update(JsonDto jsonDto) throws DaoException;

    void save(JsonDto jsonDto) throws DaoException;

    void delete(JsonDto jsonDto) throws DaoException;

//    Composite findByName(JsonDto jsonDto) throws DaoException;
}
