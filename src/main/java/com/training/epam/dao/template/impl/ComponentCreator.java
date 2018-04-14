package com.training.epam.dao.template.impl;

import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.dto.request.CreatingCriteria;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileConnector;
import com.training.epam.util.FileWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("componentCreator")
public class ComponentCreator extends AbstractTemplateComponentManager {
    private static final int ONE_ROW = 1;
    private static final int NODE_CELL_NUMBER = 0;

    public ComponentCreator(FileBrowser fileBrowser, FileWriter fileSaver, Verifiable<Cell> cellValidator) {
        super(fileBrowser, fileSaver, cellValidator);
    }

    @Override
    protected boolean execute(Sheet sheet, JsonDto jsonDto) {
        CreatingCriteria creatingCriteria = (CreatingCriteria) jsonDto;
        String parentName = creatingCriteria.getId();
        String childName = creatingCriteria.getChildName();

        Cell parentCell = findCellByName(sheet, parentName);
        if (parentCell != null) {
            createChildCell(sheet, childName, parentCell);
        } else {
            createParentCell(sheet, childName);
        }

        return true;
    }

    private void createParentCell(Sheet sheet, String nodeName) {
        int lastRowNum = sheet.getLastRowNum();
        int nodeRowNum = lastRowNum + ONE_ROW;

        Row nodeRow = sheet.createRow(nodeRowNum);
        Cell nodeCell = nodeRow.createCell(NODE_CELL_NUMBER);
        nodeCell.setCellValue(nodeName);
    }

    private void createChildCell(Sheet sheet, String name, Cell parentCell) {
        int childCellIndex = parentCell.getColumnIndex() + 1;
        int childRowIndex = parentCell.getRowIndex() + 1;

        if (isCellExists(sheet, childCellIndex, childRowIndex)) {
            int totalRowsAmount = sheet.getPhysicalNumberOfRows();
            sheet.shiftRows(childRowIndex, totalRowsAmount, ONE_ROW);
        }

        Row currentChildRow = sheet.createRow(childRowIndex);
        Cell currentChildCell = currentChildRow.createCell(childCellIndex, CellType.STRING);
        currentChildCell.setCellValue(name);
    }

    private boolean isCellExists(Sheet sheet, int cellIndex, int rowIndex) {
        boolean isCellExist = false;
        Row expectedRow = sheet.getRow(rowIndex);
        if (expectedRow != null) {
            Cell expectedCell = expectedRow.getCell(cellIndex);
            isCellExist = (expectedCell == null || !expectedCell.getStringCellValue().isEmpty());
        }

        return isCellExist;
    }
}
