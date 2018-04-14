package com.training.epam.service.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.training.epam.dao.ComponentParser;
import com.training.epam.dao.SkillRepository;
import com.training.epam.dao.impl.SkillRepositoryImpl;
import com.training.epam.dao.template.impl.ComponentCreator;
import com.training.epam.dao.template.impl.ComponentEraser;
import com.training.epam.dao.template.impl.ComponentUpdater;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.validator.impl.CellStringValidator;
import com.training.epam.entity.validator.impl.TreeStructureValidator;
import com.training.epam.factory.CompositeFactoryImpl;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import com.training.epam.service.impl.template.AbstractTemplateCacheManager;
import com.training.epam.service.impl.template.impl.CacheSearchEngine;
import com.training.epam.util.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.TestResource;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class SkillServiceImplIT {

    private SkillService underTest;

    @DataProvider
    public static Object[][] equalCompositeAndSearchingCriteria() {
        return new Object[][]{
                {TestResource.validSearchingCriteria.get(0), true},
                {TestResource.validSearchingCriteria.get(1), true}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndSearchingCriteria() {
        return new Object[][]{
                {TestResource.invalidSearchingCriteria.get(0), false},
                {TestResource.invalidSearchingCriteria.get(1), false},
                {TestResource.invalidSearchingCriteria.get(2), false}
        };
    }

    @DataProvider
    public static Object[][] equalCompositeAndCreatingCriteria() {
        return new Object[][]{
                {TestResource.validCreatingCriteria.get(0), true},
                {TestResource.validCreatingCriteria.get(1), true},
                {TestResource.validCreatingCriteria.get(2), true}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndCreatingCriteria() {
        return new Object[][]{
                {TestResource.invalidCreatingCriteria.get(0), true},
                {TestResource.invalidCreatingCriteria.get(1), true},
                {TestResource.invalidCreatingCriteria.get(2), true}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndUpdatingCriteria() {
        return new Object[][]{
                {TestResource.invalidUpdatingCriteria.get(0), false},
                {TestResource.invalidUpdatingCriteria.get(1), false},
                {TestResource.invalidUpdatingCriteria.get(2), false}
        };
    }

    @DataProvider
    public static Object[][] equalCompositeAndUpdatingCriteria() {
        return new Object[][]{
                {TestResource.validUpdatingCriteria.get(0), true},
                {TestResource.validUpdatingCriteria.get(1), true},
                {TestResource.validUpdatingCriteria.get(2), true}
        };
    }

    @DataProvider
    public static Object[][] equalCompositeAndSearchCriteria() {
        return new Object[][]{
                {TestResource.validSearchingCriteria.get(0)},
                {TestResource.validSearchingCriteria.get(1)},
                {TestResource.validSearchingCriteria.get(2)}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndSearchCriteria() {
        return new Object[][]{
                {TestResource.invalidSearchingCriteria.get(0)},
                {TestResource.invalidSearchingCriteria.get(1)},
                {TestResource.invalidSearchingCriteria.get(2)}
        };
    }

    @Before
    public void doSetup() {
        FileBrowser fileBrowser = new FileBrowser();
        CellStringValidator validator = new CellStringValidator();
        ComponentParser componentParser = new ComponentParser(new CompositeFactoryImpl(), validator, new FileReader(new FileBrowser()));
        FileWriter fileWriter = new FileWriter();
        ComponentUpdater componentUpdater = new ComponentUpdater(fileBrowser, fileWriter, validator);
        SkillRepository skillRepository = new SkillRepositoryImpl(
                componentParser, componentUpdater,
                new ComponentCreator(fileBrowser, fileWriter, validator),
                new ComponentEraser(fileBrowser, fileWriter, validator));
        AbstractTemplateCacheManager cacheManager = new CacheSearchEngine();
        underTest = new SkillServiceImpl(skillRepository, cacheManager);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndSearchingCriteria")
    public void shouldNotDeleteEntityAndFalseWhenEntityNotExists(JsonDto notExisting, boolean expectedResult) throws ServiceException {
        boolean isDeleted = underTest.delete(notExisting);
        Assert.assertFalse(isDeleted && expectedResult);
    }

    @Test
    @UseDataProvider("equalCompositeAndSearchCriteria")
    public void shouldReturnCompositeWhenNameStartsWithCriteria(SearchingCriteria criteria) throws ServiceException {
        List<Composite> actualResult = underTest.searchByCriteria(criteria);
        Assert.assertNotNull(actualResult);
        for (Composite current : actualResult) {
            Assert.assertTrue(StringUtils.startsWithIgnoreCase(current.getName(), criteria.getId()));
        }
    }

    @Test
    @UseDataProvider("equalCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityExists(JsonDto existing, boolean expectedResult) throws ServiceException {
        boolean isSaved = underTest.save(existing);
        Assert.assertTrue(isSaved && expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityNotExists(JsonDto existing, boolean expectedResult) throws ServiceException {
        boolean isSaved = underTest.save(existing);
        Assert.assertTrue(isSaved && expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndUpdatingCriteria")
    public void shouldNotUpdateEntityAndReturnFalseWhenEntityNotExists(JsonDto notExisting, boolean expectedResult) throws ServiceException {
        boolean isUpdated = underTest.updateName(notExisting);
        Assert.assertFalse(isUpdated && expectedResult);
        isUpdated = underTest.updateName(notExisting);
        Assert.assertFalse(isUpdated && expectedResult);
    }

    @Test
    @UseDataProvider("equalCompositeAndUpdatingCriteria")
    public void shouldUpdateEntityAndReturnTrueWhenEntityExists(JsonDto existing, boolean expectedResult) throws ServiceException {
        boolean isUpdated = underTest.updateName(existing);
        Assert.assertTrue(isUpdated && expectedResult);

        isUpdated = underTest.updateName(existing);
        Assert.assertFalse(isUpdated && expectedResult);
    }

    @Test(expected = ServiceException.class)
    @UseDataProvider("notEqualCompositeAndSearchCriteria")
    public void shouldReturnNullWhenNameNotStartsWithCriteria(SearchingCriteria criteria) throws ServiceException {
        underTest.searchByCriteria(criteria);
    }

    @Test
    @UseDataProvider("equalCompositeAndSearchingCriteria")
    public void shouldDeleteEntityAndReturnTrueWhenEntityExists(JsonDto existing, boolean expectedResult) throws ServiceException {
        boolean isDeleted = underTest.delete(existing);
        Assert.assertTrue(isDeleted && expectedResult);

        isDeleted = underTest.delete(existing);
        Assert.assertFalse(isDeleted);
    }

}
