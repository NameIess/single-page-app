package com.training.epam.dao;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.util.FileBrowser;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    public ComponentParser(CompositeFactory compositeFactory, FileBrowser fileBrowser) {
        this.compositeFactory = compositeFactory;
        this.fileBrowser = fileBrowser;
    }

    public List<Composite> parseFile(String fileName) throws DaoException {
            List<Composite> componentList = new ArrayList<>();
            Workbook workbook = getDataFromFile(fileName);

            for (Sheet sheet : workbook) {
                Composite component = buildComposite(sheet);
                componentList.add(component);
            }

            return componentList;
    }

    private Workbook getDataFromFile(String fileName) throws DaoException {
        File file = fileBrowser.getFile(fileName);
        try (Workbook workbook = WorkbookFactory.create(file)) {
            return workbook;

        } catch (InvalidFormatException | IOException e) {
            throw new DaoException("Exception within getDataFromFile(): " + e.getMessage(), e);
        }
    }

    private Composite buildComposite(Sheet sheet) {
        Composite component = compositeFactory.create();
        Map<Integer, Composite> parentMap = new HashMap<>();
        int nodeNesting = 0;
        Composite current = null;

        for (Row row : sheet) {
            for (Cell cell : row) {
                if (!cell.getStringCellValue().isEmpty() && cell.getCellTypeEnum() == CellType.STRING) {

                    int childNesting = cell.getColumnIndex();

                    if (childNesting == NODE_CELL_NUMBER) {
                        current = compositeFactory.addChild(component, cell);

                    } else if (childNesting > nodeNesting) {
                        current = compositeFactory.addChild(current, cell);

                    } else if (childNesting <= nodeNesting) {
                        int parentNesting = childNesting - 1;
                        Composite parent = parentMap.get(parentNesting);
                        current = compositeFactory.addChild(parent, cell);
                    }

                    parentMap.put(childNesting, current);
                    nodeNesting = childNesting;
                }
            }
        }

        return component;
    }
}
