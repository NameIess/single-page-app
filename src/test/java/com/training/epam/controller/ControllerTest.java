package com.training.epam.controller;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import com.training.epam.resources.TestResource;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ControllerTest {
    private Controller underTest;
    private SkillService skillService;

    @Before
    public void doSetup() {
        skillService = mock(SkillService.class);
        underTest = new Controller(skillService);
    }

    @Test
    public void shouldReturnListWhenDataExist() throws ServiceException {
        when(skillService.findAll()).thenReturn(TestResource.compositeList);

        List<Composite> actualResult = underTest.findAll();
        verify(skillService, times(TestResource.ONE_TIME)).findAll();

        Assert.assertEquals(actualResult, TestResource.compositeList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowWhenDataSourceError() throws ServiceException {
        when(skillService.findAll()).thenThrow(ServiceException.class);

        underTest.findAll();
    }

    @Test
    public void shouldUpdateAndReturnTrueWhenDataExist() throws ServiceException {       // method name??
        boolean expectedResult = true;
        when(skillService.updateName(any(UpdatingCriteria.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.updateName(TestResource.UPDATING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).updateName(any(UpdatingCriteria.class));

        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test
    public void shouldNotUpdateAndReturnFalseWhenDataNotExist() throws ServiceException {
        boolean expectedResult = false;
        when(skillService.updateName(any(UpdatingCriteria.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.updateName(TestResource.UPDATING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).updateName(any(UpdatingCriteria.class));

        Assert.assertFalse(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInseadUpdateWhenDataSourceError() throws ServiceException {
        when(skillService.updateName(any(UpdatingCriteria.class))).thenThrow(ServiceException.class);

        underTest.updateName(TestResource.UPDATING_CRITERIA);
    }

    @Test
    public void shouldFindAndReturnEntityListWhenDataExist() throws ServiceException {
        when(skillService.searchByCriteria(any(SearchingCriteria.class))).thenReturn(TestResource.compositeList);

        List<Composite> actualResult = underTest.searchByCriteria(TestResource.SEARCHING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).searchByCriteria(any(SearchingCriteria.class));

        Assert.assertEquals(actualResult, TestResource.compositeList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadUpdateWhenDataSourceErrorOrDataNotFound() throws ServiceException {
        when(skillService.searchByCriteria(any(SearchingCriteria.class))).thenThrow(ServiceException.class);

        underTest.searchByCriteria(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldSaveEntityAndReturnTrueWhenDataExist() throws ServiceException {
        boolean expectedResult = true;
        when(skillService.save(any(CreatingCriteria.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.save(TestResource.CREATING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).save(any(CreatingCriteria.class));

        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadSaveWhenDataSourceErrorOrDataNotFound() throws ServiceException {
        when(skillService.save(any(CreatingCriteria.class))).thenThrow(ServiceException.class);

        underTest.save(TestResource.CREATING_CRITERIA);
    }

    @Test
    public void shouldDeleteEntityAndReturnTrueWhenDataExist() throws ServiceException {
        boolean expectedResult = true;
        when(skillService.delete(any(SearchingCriteria.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).delete(any(SearchingCriteria.class));

        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test
    public void shouldNotDeleteEntityAndReturnFalseWhenDataNotExist() throws ServiceException {
        boolean expectedResult = false;
        when(skillService.delete(any(SearchingCriteria.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(skillService, times(TestResource.ONE_TIME)).delete(any(SearchingCriteria.class));

        Assert.assertFalse(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInseadDeleteWhenDataSourceError() throws ServiceException {
        when(skillService.delete(any(SearchingCriteria.class))).thenThrow(ServiceException.class);

        underTest.delete(TestResource.SEARCHING_CRITERIA);
    }
}
