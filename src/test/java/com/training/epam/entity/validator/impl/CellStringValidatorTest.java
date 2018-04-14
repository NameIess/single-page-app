package com.training.epam.entity.validator.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import resources.TestResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CellStringValidatorTest {
    private CellStringValidator underTest;


    @Before
    public void doSetup() {
        underTest = new CellStringValidator();
    }

    @DataProvider
    public static Object[][] validCell() {
        return new Object[][]{
                {TestResource.validCells.get(0), true},
                {TestResource.validCells.get(1), true},
                {TestResource.validCells.get(2), true},
                {TestResource.validCells.get(3), true},
                {TestResource.validCells.get(4), true}
        };
    }

    @DataProvider
    public static Object[][] invalidCell() {
        return new Object[][]{
                {TestResource.invalidCells.get(0), false},
                {TestResource.invalidCells.get(1), false},
                {TestResource.invalidCells.get(2), false},
                {TestResource.invalidCells.get(3), false},
                {TestResource.invalidCells.get(4), false},
                {TestResource.invalidCells.get(5), false}
        };
    }


    @Test
    @UseDataProvider("validCell")
    public void shouldReturnTrueWhenCellNotEmptyString(Cell cell, boolean expectedResult) {
        boolean actualResult = underTest.isValid(cell);
        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test
    @UseDataProvider("invalidCell")
    public void shouldReturnFalseWhenCellEmptyOrNotStringOrTooLong(Cell cell, boolean expectedResult) {
        boolean actualResult = underTest.isValid(cell);
        Assert.assertFalse(actualResult && expectedResult);
    }
}
