package com.training.epam.entity.validator.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.training.epam.resources.TestResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CellStringValidatorTest {
    private CellStringValidator cellValidator;

    @Before
    public void doSetup() {
        cellValidator = new CellStringValidator();
    }

    @DataProvider
    public static Object[][] equalCompositeAndCriteria() {
        return new Object[][]{
                {TestResource.compositeList.get(0), TestResource.validSearchingCriteria.get(0)},
                {TestResource.compositeList.get(1), TestResource.validSearchingCriteria.get(1)},
                {TestResource.compositeList.get(2), TestResource.validSearchingCriteria.get(2)}
        };
    }


    @Test
    public void shouldReturnTrueWhenCellNotEmptyString() {

    }
}
