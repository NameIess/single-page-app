package com.training.epam.dao.impl.integration;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.training.epam.dao.ComponentParser;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.dao.impl.SkillRepositoryImpl;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.dao.template.impl.ComponentCreator;
import com.training.epam.dao.template.impl.ComponentEraser;
import com.training.epam.dao.template.impl.ComponentUpdater;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.entity.validator.impl.CellStringValidator;
import com.training.epam.entity.validator.impl.TreeStructureValidator;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.factory.CompositeFactoryImpl;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileReader;
import com.training.epam.util.FileWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.TestResource;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class SkillRepositoryImplTest {
    private SkillRepositoryImpl underTest;

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
                {TestResource.invalidUpdatingCriteria.get(0), true},
                {TestResource.invalidUpdatingCriteria.get(1), true},
                {TestResource.invalidUpdatingCriteria.get(2), true}
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

    @Before
    public void doSetup() {
        CompositeFactory compositeFactory = new CompositeFactoryImpl();
        FileBrowser fileBrowser = new FileBrowser();
        Verifiable<Cell> validator = new CellStringValidator();
        FileWriter fileSaver = new FileWriter();
        FileReader fileReader = new FileReader(new FileBrowser());

        ComponentParser componentParser = new ComponentParser(compositeFactory, validator, fileReader);
        AbstractTemplateComponentManager componentUpdater = new ComponentUpdater(fileBrowser, fileSaver, validator);
        AbstractTemplateComponentManager componentCreator = new ComponentCreator(fileBrowser, fileSaver, validator);
        AbstractTemplateComponentManager componentEraser = new ComponentEraser(fileBrowser, fileSaver, validator);
        underTest = new SkillRepositoryImpl(componentParser, componentUpdater, componentCreator, componentEraser);
    }

    @Test
    public void shouldFindAllEntitiesAndReturnListWhenEntityExists() throws DaoException {
        List<Composite> actualResult = underTest.findAll();

        Assert.assertNotNull(actualResult);
        Assert.assertFalse(actualResult.isEmpty());
        Assert.assertEquals(actualResult, TestResource.validCompositeList);
    }

    @Test
    @UseDataProvider("equalCompositeAndSearchingCriteria")
    public void shouldDeleteEntityAndReturnTrueWhenEntityExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isDeleted = underTest.delete(existing);
        Assert.assertTrue(isDeleted && expectedResult);

        isDeleted = underTest.delete(existing);
        Assert.assertFalse(isDeleted);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndSearchingCriteria")
    public void shouldNotDeleteEntityAndFalseTrueWhenEntityNotExists(JsonDto notExisting, boolean expectedResult) throws DaoException {
        boolean isDeleted = underTest.delete(notExisting);
        Assert.assertFalse(isDeleted && expectedResult);
    }

    @Test
    @UseDataProvider("equalCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isSaved = underTest.save(existing);
        Assert.assertTrue(isSaved && expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityNotExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isSaved = underTest.save(existing);
        Assert.assertTrue(isSaved && expectedResult);
    }

    @Test
    @UseDataProvider("equalCompositeAndUpdatingCriteria")
    public void shouldUpdateEntityAndReturnTrueWhenEntityExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isUpdated = underTest.update(existing);
        Assert.assertTrue(isUpdated && expectedResult);

        isUpdated = underTest.update(existing);
        Assert.assertFalse(isUpdated && expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndUpdatingCriteria")
    public void shouldNotUpdateEntityAndReturnFalseWhenEntityNotExists(JsonDto notExisting, boolean expectedResult) throws DaoException {
        boolean isUpdated = underTest.update(notExisting);
        Assert.assertFalse(isUpdated && expectedResult);
        isUpdated = underTest.update(notExisting);
        Assert.assertFalse(isUpdated && expectedResult);
    }
}
