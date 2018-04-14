package com.training.epam.dao.template.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.entity.validator.impl.CellStringValidator;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.TestResource;

@RunWith(DataProviderRunner.class)
public class AbstractTemplateComponentManagerComponentCreatorTest {
    private AbstractTemplateComponentManager underTest;

    @Before
    public void doSetup() {
        FileBrowser fileBrowser = new FileBrowser();
        Verifiable<Cell> cellValidator = new CellStringValidator();
        FileWriter fileSaver = new FileWriter();
        underTest = new ComponentCreator(fileBrowser, fileSaver, cellValidator);
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

    @Test
    @UseDataProvider("equalCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isSaved = underTest.updateFile(TestResource.TEST_FILE_NAME, existing);
        Assert.assertTrue(isSaved && expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndCreatingCriteria")
    public void shouldSaveEntityAndReturnTrueWhenParentEntityNotExists(JsonDto existing, boolean expectedResult) throws DaoException {
        boolean isSaved = underTest.updateFile(TestResource.TEST_FILE_NAME, existing);
        Assert.assertTrue(isSaved && expectedResult);
    }
}
