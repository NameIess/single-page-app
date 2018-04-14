package com.training.epam.service.impl.template;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import resources.TestResource;
import com.training.epam.service.impl.template.impl.CacheSearchEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(DataProviderRunner.class)
public class AbstractTemplateCacheManagerTest {
    private AbstractTemplateCacheManager underTest;

    @DataProvider
    public static Object[][] equalCompositeAndCriteria() {
        return new Object[][]{
                {TestResource.compositeList, TestResource.validSearchingCriteria.get(0), 1},
                {TestResource.compositeList, TestResource.validSearchingCriteria.get(1), 1},
                {TestResource.compositeList, TestResource.validSearchingCriteria.get(2), 2}
        };
    }

    @DataProvider
    public static Object[][] notEqualCompositeAndCriteria() {
        return new Object[][]{
                {TestResource.compositeList, TestResource.invalidSearchingCriteria.get(0), 0},
                {TestResource.compositeList, TestResource.invalidSearchingCriteria.get(1), 0},
                {TestResource.compositeList, TestResource.invalidSearchingCriteria.get(2), 0}
        };
    }

    @Before
    public void doSetup() {
        underTest = new CacheSearchEngine();
    }

    @Test
    @UseDataProvider("equalCompositeAndCriteria")
    public void shouldReturnCompositeListWhenNameStartsWithPrefix(List<Composite> list, JsonDto prefix, int expectedResult) {
        verifyGivenData(list, prefix, expectedResult);
    }

    @Test
    @UseDataProvider("notEqualCompositeAndCriteria")
    public void shouldNotReturnListWhenNameNotStartsWithPrefix(List<Composite> list, JsonDto prefix, int expectedResult) {
        verifyGivenData(list, prefix, expectedResult);
    }

    private void verifyGivenData(List<Composite> list, JsonDto prefix, int expectedResult) {
        List<Composite> composites = underTest.handleCriteria(list, prefix);
        int actualResult = composites.size();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
