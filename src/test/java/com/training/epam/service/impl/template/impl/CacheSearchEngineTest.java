package com.training.epam.service.impl.template.impl;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.resources.TestResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class CacheSearchEngineTest {
    private CacheSearchEngine underTest;

    @DataProvider
    public static Object[][] equalCompositeAndCriteria() {
        return new Object[][]{
                {TestResource.compositeList.get(0), TestResource.validSearchingCriteria.get(0)},
                {TestResource.compositeList.get(1), TestResource.validSearchingCriteria.get(1)},
                {TestResource.compositeList.get(2), TestResource.validSearchingCriteria.get(2)}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndCriteria() {
        return new Object[][]{
                {TestResource.compositeList.get(0), TestResource.invalidSearchingCriteria.get(0)},
                {TestResource.compositeList.get(1), TestResource.invalidSearchingCriteria.get(1)},
                {TestResource.compositeList.get(2), TestResource.invalidSearchingCriteria.get(2)}
        };
    }

    @Before
    public void doSetup() {
        underTest = new CacheSearchEngine();
    }

    @Test
    @UseDataProvider("equalCompositeAndCriteria")
    public void shouldReturnCompositeWhenNameStartsWithCriteria(Composite expectedResult, JsonDto criteria) {
        Composite actualResult = underTest.execute(expectedResult, criteria);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndCriteria")
    public void shouldReturnNullWhenNameNotStartsWithCriteria(Composite expectedResult, JsonDto criteria) {
        Composite actualResult = underTest.execute(expectedResult, criteria);
        Assert.assertNotEquals(actualResult, expectedResult);
    }
}
