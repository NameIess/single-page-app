package com.training.epam.controller;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/developer")
public class Controller {
    private static final Logger Log = LogManager.getLogger(Controller.class.getSimpleName());
    private SkillService skillService;

    @Autowired
    public Controller(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = "/skill/list/", method = RequestMethod.GET)
    public List<Composite> findAll() throws ServiceException {
        List<Composite> componentList = skillService.findAll();

        return componentList;
    }

    @RequestMapping(value = "/skill/update", method = RequestMethod.PUT)
    public boolean updateName(@Valid @RequestBody UpdatingCriteria updateCriteria) throws ServiceException {
        Log.info("updateName(): " + updateCriteria);
        boolean isUpdated = skillService.updateName(updateCriteria);
        return isUpdated;
    }

    @RequestMapping(value = "/skill/", method = RequestMethod.PUT)
    public List<Composite> searchByCriteria(@Valid @RequestBody SearchingCriteria currentName) throws ServiceException {
        Log.info("searchByName(): " + currentName);
        List<Composite> compositeList = skillService.searchByCriteria(currentName);

        return compositeList;
    }

    @RequestMapping(value = "/skill/add", method = RequestMethod.PUT)
    public boolean save(@Valid @RequestBody CreatingCriteria creatingCriteria) throws ServiceException {
        Log.info("createNode(): child name " + creatingCriteria.getChildName() + " to parent " + creatingCriteria.getId());
        boolean isSaved = skillService.save(creatingCriteria);
        return isSaved;
    }

    @RequestMapping(value = "/skill/", method = RequestMethod.DELETE)
    public boolean delete(@Valid @RequestBody SearchingCriteria searchingCriteria) throws ServiceException {
        Log.info("createNode(): child name " + searchingCriteria.getId());
        boolean isDeleted = skillService.delete(searchingCriteria);
        return isDeleted;
    }

}
