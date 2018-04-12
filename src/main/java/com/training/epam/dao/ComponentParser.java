package com.training.epam.dao;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.util.FileBrowser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("xlsComponentParser")
public class ComponentParser {
    private static final int NODE_CELL_NUMBER = 0;
    private CompositeFactory compositeFactory;
    private FileBrowser fileBrowser;
    private Verifiable<Cell> cellValidator;

    @Autowired
    public ComponentParser(CompositeFactory compositeFactory, FileBrowser fileBrowser, Verifiable<Cell> cellValidator) {
        this.compositeFactory = compositeFactory;
        this.fileBrowser = fileBrowser;
        this.cellValidator = cellValidator;
    }

    public List<Composite> parseFile(String fileName) throws DaoException {
        List<Composite> componentList = new ArrayList<>();
        Workbook workbook = redFile(fileName);

        for (Sheet sheet : workbook) {
            Composite component = buildComposite(sheet);
            componentList.add(component);
        }

        return componentList;
    }

    private Workbook redFile(String fileName) throws DaoException {     // fileReader??
        File file = fileBrowser.getFile(fileName);
        try (Workbook workbook = WorkbookFactory.create(file)) {
            return workbook;

        } catch (InvalidFormatException | IOException e) {
            throw new DaoException("Exception within redFile(): " + e.getMessage(), e);
        }
    }

    private Composite buildComposite(Sheet sheet) {
        Composite component = compositeFactory.create();
        Map<Integer, Composite> parentMap = new HashMap<>();
        int nodeNesting = 0;
        Composite current = null;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cellValidator.isValid(cell)) {

                    int childNesting = cell.getColumnIndex();

                    if (childNesting == NODE_CELL_NUMBER) {
                        current = addChild(component, cell);

                    } else if (childNesting > nodeNesting) {
                        current = addChild(current, cell);

                    } else if (childNesting <= nodeNesting) {
                        int parentNesting = childNesting - 1;
                        Composite parent = parentMap.get(parentNesting);
                        current = addChild(parent, cell);
                    }

                    parentMap.put(childNesting, current);
                    nodeNesting = childNesting;
                }
            }
        }

        return component;
    }

    private Composite addChild(Composite parent, Cell cell) {
        String cellValue = cell.getStringCellValue();
        Composite current = compositeFactory.addChildAndGet(parent, cellValue);

        return current;
    }
}
