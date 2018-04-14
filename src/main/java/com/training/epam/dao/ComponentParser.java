package com.training.epam.dao;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.Composite;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.util.FileReader;
import com.training.epam.util.XLSBusinessException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("xlsComponentParser")
public class ComponentParser {
    private static final int NODE_CELL_NUMBER = 0;
    private CompositeFactory compositeFactory;
    private Verifiable<Cell> cellValidator;
    private FileReader fileReader;

    @Autowired
    public ComponentParser(CompositeFactory compositeFactory, Verifiable<Cell> cellValidator, FileReader fileReader) {
        this.compositeFactory = compositeFactory;
        this.cellValidator = cellValidator;
        this.fileReader = fileReader;
    }

    public List<Composite> parseFile(String fileName) throws DaoException {
        try {
            List<Composite> componentList = new ArrayList<>();
            Workbook workbook = fileReader.readFile(fileName);

            for (Sheet sheet : workbook) {
                Composite component = buildComposite(sheet);
                componentList.add(component);
            }

            return componentList;
        } catch (IOException | InvalidFormatException e) {
            throw new DaoException("Error during file parsing. " + e.getMessage(), e);
        } catch (XLSBusinessException e) {
            throw new DaoException("Invalid file structure. " + e.getMessage(), e);
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
