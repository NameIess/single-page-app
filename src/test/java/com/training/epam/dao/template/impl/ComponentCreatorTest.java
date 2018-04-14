package com.training.epam.dao.template.impl;

import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.entity.validator.impl.CellStringValidator;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import resources.TestResource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComponentCreator.class)
@PowerMockIgnore("javax.management.*")
public class ComponentCreatorTest {
    private AbstractTemplateComponentManager underTest;

    @Before
    public void doSetup() {
        FileBrowser fileBrowser = new FileBrowser();
        Verifiable<Cell> cellValidator = new CellStringValidator();
        FileWriter fileSaver = new FileWriter();
        underTest = new ComponentCreator(fileBrowser, fileSaver, cellValidator);
    }

    @Test
    public void shouldCreateChildCellWhenParentCellIsNull() throws Exception {
        AbstractTemplateComponentManager spyUnderTest = spy(underTest);
        doReturn(null).when(spyUnderTest, TestResource.FIND_CELL_BY_NAME, any(Cell.class), anyString());
        boolean isCreated = spyUnderTest.updateFile(TestResource.TEST_FILE_NAME, TestResource.CREATING_CRITERIA);
        verifyPrivate(spyUnderTest, times(1)).invoke(TestResource.CREATE_PARENT_CELL, any(Sheet.class), anyString());
        Assert.assertTrue(isCreated);
    }
}
