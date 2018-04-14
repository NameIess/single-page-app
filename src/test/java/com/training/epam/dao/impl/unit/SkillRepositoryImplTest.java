package com.training.epam.dao.impl.unit;

import com.training.epam.dao.ComponentParser;
import com.training.epam.dao.exception.DaoException;
import com.training.epam.dao.impl.SkillRepositoryImpl;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.JsonDto;
import resources.TestResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SkillRepositoryImplTest {
    private SkillRepositoryImpl underTest;
    private ComponentParser componentParser;
    private AbstractTemplateComponentManager componentUpdater;
    private AbstractTemplateComponentManager componentCreator;
    private AbstractTemplateComponentManager componentEraser;


    @Before
    public void doSetup() {
        componentParser = mock(ComponentParser.class);
        componentUpdater = mock(AbstractTemplateComponentManager.class);
        componentCreator = mock(AbstractTemplateComponentManager.class);
        componentEraser = mock(AbstractTemplateComponentManager.class);
        underTest = new SkillRepositoryImpl(componentParser, componentUpdater, componentCreator, componentEraser);
    }

    @Test
    public void shouldFindAllEntitiesAndReturnListWhenEntityExists() throws DaoException {
        doReturn(TestResource.compositeList).when(componentParser).parseFile(anyString());

        List<Composite> actualResult = underTest.findAll();
        verify(componentParser, times(TestResource.ONE_TIME)).parseFile(anyString());

        Assert.assertEquals(actualResult, TestResource.compositeList);
    }

    @Test(expected = DaoException.class)
    public void shouldThrowDaoExceptionInsteadFindAllWhenDataSourceError() throws DaoException {
        when(componentParser.parseFile(anyString())).thenThrow(DaoException.class);

        underTest.findAll();
    }

    @Test
    public void shouldUpdateEntityAndReturnTrueWhenEntityExists() throws DaoException {
        boolean expectedResult = true;
        when(componentUpdater.updateFile(anyString(), any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.update(TestResource.SEARCHING_CRITERIA);
        verify(componentUpdater, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));

        Assert.assertTrue(expectedResult && actualResult);
    }

    @Test
    public void shouldNotUpdateEntityAndReturnFalseWhenEntityNotExists() throws DaoException {
        boolean expectedResult = false;
        when(componentUpdater.updateFile(anyString(), any(JsonDto.class))).thenReturn(false);

        boolean actualResult = underTest.update(TestResource.SEARCHING_CRITERIA);
        verify(componentUpdater, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));;

        Assert.assertFalse(expectedResult && actualResult);
    }

    @Test(expected = DaoException.class)
    public void shouldThrowDaoExceptionInsteadUpdateWhenDataSourceError() throws DaoException {
        when(componentUpdater.updateFile(anyString(), any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.update(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldSaveEntityAndReturnTrueWhenEntityExists() throws DaoException {
        boolean expectedResult = true;
        when(componentCreator.updateFile(anyString(), any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.save(TestResource.SEARCHING_CRITERIA);
        verify(componentCreator, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));

        Assert.assertTrue(expectedResult && actualResult);
    }

    @Test
    public void shouldSaveEntityAndReturnTrueWhenEntityNotExists() throws DaoException {
        boolean expectedResult = true;
        when(componentCreator.updateFile(anyString(), any(JsonDto.class))).thenReturn(true);

        boolean actualResult = underTest.save(TestResource.SEARCHING_CRITERIA);
        verify(componentCreator, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));;

        Assert.assertTrue(expectedResult && actualResult);
    }

    @Test(expected = DaoException.class)
    public void shouldThrowDaoExceptionInsteadSaveWhenDataSourceError() throws DaoException {
        when(componentCreator.updateFile(anyString(), any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.save(TestResource.SEARCHING_CRITERIA);
    }

    @Test
    public void shouldDeleteEntityAndReturnTrueWhenEntityExists() throws DaoException {
        boolean expectedResult = true;
        when(componentEraser.updateFile(anyString(), any(JsonDto.class))).thenReturn(expectedResult);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(componentEraser, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));

        Assert.assertTrue(expectedResult && actualResult);
    }

    @Test
    public void shouldNotDeleteEntityAndReturnFalseWhenEntityNotExists() throws DaoException {
        boolean expectedResult = true;
        when(componentEraser.updateFile(anyString(), any(JsonDto.class))).thenReturn(true);

        boolean actualResult = underTest.delete(TestResource.SEARCHING_CRITERIA);
        verify(componentEraser, times(TestResource.ONE_TIME)).updateFile(anyString(), any(JsonDto.class));;

        Assert.assertTrue(expectedResult && actualResult);
    }

    @Test(expected = DaoException.class)
    public void shouldThrowDaoExceptionInsteadDeleteWhenDataSourceError() throws DaoException {
        when(componentEraser.updateFile(anyString(), any(JsonDto.class))).thenThrow(DaoException.class);

        underTest.delete(TestResource.SEARCHING_CRITERIA);
    }

}
