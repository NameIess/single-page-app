package com.training.epam.service.impl;

import com.training.epam.dao.SkillRepository;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.resources.TestResource;
import com.training.epam.service.SkillService;
import com.training.epam.service.exception.ServiceException;
import com.training.epam.service.impl.template.AbstractTemplateCacheManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class SkillServiceImplTest {

    private SkillService underTest;
    private SkillRepository skillRepository;
    private AbstractTemplateCacheManager cacheManager;

    @Before
    public void doSetup() {
        skillRepository = mock(SkillRepository.class);
        cacheManager = mock(AbstractTemplateCacheManager.class);
        underTest = new SkillServiceImpl(skillRepository, cacheManager);
    }

    @Test
    public void shouldDeleteEntityAndReturnTrueWhenEntityExists() throws ServiceException, DaoException {
        boolean expectedResult = true;
        when(skillRepository.delete(any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(skillRepository, times(TestResource.ONE_TIME)).delete(TestResource.SEARCHING_CRITERIA);
        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test
    public void shouldNotDeleteEntityAndReturnFalseWhenEntityNotExists() throws DaoException, ServiceException {
        boolean expectedResult = false;
        when(skillRepository.delete(any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(skillRepository, times(TestResource.ONE_TIME)).delete(TestResource.SEARCHING_CRITERIA);
        Assert.assertFalse(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadDeleteWhenDataSourceError() throws DaoException, ServiceException {
        when(skillRepository.delete(any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.delete(TestResource.SEARCHING_CRITERIA);
    }


    @Test
    public void shouldUpdateEntityAndReturnTrueWhenEntityExists() throws ServiceException, DaoException {
        boolean expectedResult = true;
        when(skillRepository.update(any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.updateName(TestResource.SEARCHING_CRITERIA);
        verify(skillRepository, times(TestResource.ONE_TIME)).update(TestResource.SEARCHING_CRITERIA);
        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test
    public void shouldNotUpdateEntityAndReturnFalseWhenEntityNotExists() throws DaoException, ServiceException {
        boolean expectedResult = false;
        when(skillRepository.update(any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.updateName(TestResource.SEARCHING_CRITERIA);
        verify(skillRepository, times(TestResource.ONE_TIME)).update(TestResource.SEARCHING_CRITERIA);
        Assert.assertFalse(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadUpdateWhenDataSourceError() throws DaoException, ServiceException {
        when(skillRepository.update(any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.updateName(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldFindEntityAndReturnListWhenEntityExists() throws ServiceException {
        when(cacheManager.handleCriteria(anyList(), any(JsonDto.class))).thenReturn(TestResource.compositeList);

        List<Composite> actualResult = underTest.searchByCriteria(TestResource.SEARCHING_CRITERIA);

        Assert.assertEquals(actualResult, TestResource.compositeList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionWhenEntityNotExists() throws DaoException, ServiceException {
        when(cacheManager.handleCriteria(anyList(), any(JsonDto.class))).thenReturn(null);

        underTest.searchByCriteria(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldSaveEntityAndReturnTrueWhenEntityValid() throws ServiceException, DaoException {
        boolean expectedResult = true;
        when(skillRepository.save(any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.save(TestResource.SEARCHING_CRITERIA);
        verify(skillRepository, times(TestResource.ONE_TIME)).save(TestResource.SEARCHING_CRITERIA);
        Assert.assertTrue(actualResult && expectedResult);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadSaveWhenDatasourceError() throws DaoException, ServiceException {
        when(skillRepository.save(any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.save(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldFindAllEntitiesAndReturnListWhenEntityExists() throws DaoException, ServiceException {
        doReturn(TestResource.compositeList).when(skillRepository).findAll();

        List<Composite> actualResult = underTest.findAll();
        verify(skillRepository, times(TestResource.ONE_TIME)).findAll();

        Assert.assertEquals(actualResult, TestResource.compositeList);
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadFindAllWhenDataSourceError() throws DaoException, ServiceException {
        when(skillRepository.findAll()).thenThrow(DaoException.class);

        underTest.findAll();
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadFindAllWhenDataNotFound() throws DaoException, ServiceException {
        when(skillRepository.findAll()).thenReturn(TestResource.emptyList);

        underTest.findAll();
    }

    @Test(expected = ServiceException.class)
    public void shouldThrowServiceExceptionInsteadFindAllWhenDataIsNull() throws DaoException, ServiceException {
        when(skillRepository.findAll()).thenReturn(null);

        underTest.findAll();
    }
}
