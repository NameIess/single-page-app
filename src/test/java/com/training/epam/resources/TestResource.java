package com.training.epam.resources;

import com.training.epam.entity.Composite;
import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import com.training.epam.resources.factory.CompositeFactory;
import com.training.epam.resources.factory.JsonDtoFactory;
import com.training.epam.resources.factory.impl.CompositeFactoryImpl;
import com.training.epam.resources.factory.impl.CreatingCriteriaFactory;
import com.training.epam.resources.factory.impl.SearchCriteriaFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.ArrayList;
import java.util.List;

public class TestResource {
    public static final JsonDtoFactory<SearchingCriteria> SEARCHING_CRITERIA_FACTORY = new SearchCriteriaFactory();
    public static final JsonDtoFactory<CreatingCriteria> CREATING_CRITERIA_FACTORY = new CreatingCriteriaFactory();
    public static final CompositeFactory COMPOSITE_FACTORY = new CompositeFactoryImpl();
    public static final int ONE_TIME = 1;
    public static final String SAMPLE_STRING = "Ab_9";
    public static final SearchingCriteria SEARCHING_CRITERIA = SEARCHING_CRITERIA_FACTORY.create();
    public static final CreatingCriteria CREATING_CRITERIA = CREATING_CRITERIA_FACTORY.create();
    public static final UpdatingCriteria UPDATING_CRITERIA = new UpdatingCriteria();
    public static List<Composite> emptyList = new ArrayList<>();
    public static List<Composite> compositeList = new ArrayList<>();
    static {
        Composite first = COMPOSITE_FACTORY.create("ALPHA");
        compositeList.add(first);
        Composite second = COMPOSITE_FACTORY.create("Beta");
        compositeList.add(second);
        Composite third = COMPOSITE_FACTORY.create("Gamma");
        Composite fourth = COMPOSITE_FACTORY.create("Gamma");
        third.addComponent(fourth);
        compositeList.add(third);

    }

    public static List<JsonDto> validSearchingCriteria = new ArrayList<>();
    static {
        SearchingCriteria first = SEARCHING_CRITERIA_FACTORY.create("ALPHA");
        validSearchingCriteria.add(first);
        SearchingCriteria second = SEARCHING_CRITERIA_FACTORY.create("Beta");
        validSearchingCriteria.add(second);
        SearchingCriteria third = SEARCHING_CRITERIA_FACTORY.create("Gamma");
        validSearchingCriteria.add(third);
        SearchingCriteria fourth = SEARCHING_CRITERIA_FACTORY.create("Gamma");
        validSearchingCriteria.add(fourth);
    }


    public static List<SearchingCriteria> invalidSearchingCriteria = new ArrayList<>();
    static {
        SearchingCriteria firstCriteria = SEARCHING_CRITERIA_FACTORY.create("124324");
        invalidSearchingCriteria.add(firstCriteria);
        SearchingCriteria secondCriteria = SEARCHING_CRITERIA_FACTORY.create("     ");
        invalidSearchingCriteria.add(secondCriteria);
        SearchingCriteria thirdCriteria = SEARCHING_CRITERIA_FACTORY.create("unknown_type");
        invalidSearchingCriteria.add(thirdCriteria);
    }

//
//    public static List<Cell> validStrings = new ArrayList<>();
//    static {
//        validStrings.add(new XSSFCell()"Sample");
//        validStrings.add("12345");
//        validStrings.add("1 + 9");
//        validStrings.add("C#%!@523Aq");
//        validStrings.add("кириллица");
//        validStrings.add("СтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСт");
//        validStrings.add("м");
//        validStrings.add("СтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимальнойДлиныСтрокаМаксимально");
//        validStrings.add("Sample");
//    }

}
