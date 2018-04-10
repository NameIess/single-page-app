package com.training.epam.dao.impl;

import com.training.epam.dao.SkillRepository;
import com.training.epam.dao.ComponentParser;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("skillRepository")
public class SkillRepositoryImpl implements SkillRepository {
    private static final String DATA_FILE = "SkillMatrix.xlsx";
    private ComponentParser componentParser;
    private AbstractTemplateComponentManager componentUpdater;
    private AbstractTemplateComponentManager componentCreator;
    private AbstractTemplateComponentManager componentEraser;

    @Autowired
    public SkillRepositoryImpl(ComponentParser componentParser, @Qualifier("componentUpdater") AbstractTemplateComponentManager componentUpdater, @Qualifier("componentCreator") AbstractTemplateComponentManager componentCreator, @Qualifier("componentEraser") AbstractTemplateComponentManager componentEraser) {
        this.componentParser = componentParser;
        this.componentUpdater = componentUpdater;
        this.componentCreator = componentCreator;
        this.componentEraser = componentEraser;
    }


    @Override
    public List<Composite> findAll() throws DaoException {
        List<Composite> componentList = componentParser.parseFile(DATA_FILE);
        return componentList;
    }

    @Override
    public void update(JsonDto jsonDto) throws DaoException {
        componentUpdater.updateFile(DATA_FILE, jsonDto);
    }

    @Override
    public void save(JsonDto jsonDto) throws DaoException {
        componentCreator.updateFile(DATA_FILE, jsonDto);
    }

    @Override
    public void delete(JsonDto jsonDto) throws DaoException {
        componentEraser.updateFile(DATA_FILE, jsonDto);
    }
}
