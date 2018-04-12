package com.training.epam.dao.template.impl;

import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("componentEraser")
public class ComponentEraser extends AbstractTemplateComponentManager {

    public ComponentEraser(FileBrowser fileBrowser, FileWriter fileSaver, Verifiable<Cell> cellValidator) {
        super(fileBrowser, fileSaver, cellValidator);
    }

    @Override
    protected boolean execute(Sheet sheet, JsonDto jsonDto) {
        SearchingCriteria searchingCriteria = (SearchingCriteria) jsonDto;
        String cellName = searchingCriteria.getId();

        Cell cellToRemove = findCellByName(sheet, cellName);

        boolean isDeleted = false;

        if (cellToRemove != null) {
            List<Row> toRemoveRows = findRowsToRemove(sheet, cellToRemove);
            removeRows(sheet, toRemoveRows);
            isDeleted = true;
        }

        return isDeleted;
    }

    private void removeRows(Sheet sheet, List<Row> rowList) {
        for (Row row : rowList) {
            int rowIndex = row.getRowNum() + 1;
            sheet.shiftRows(rowIndex, sheet.getLastRowNum(), -1);
        }
    }



    private List<Row> findRowsToRemove(Sheet sheet, Cell parentCell) {
        Row parentRow = parentCell.getRow();
        int rowNum = parentRow.getRowNum() + 1;
        int parentNesting = parentCell.getColumnIndex();

        List<Row> toRemoveRows = new ArrayList<>();
        toRemoveRows.add(parentRow);

        boolean isSearchActual = true;
        for (int i = rowNum; i < sheet.getPhysicalNumberOfRows() && isSearchActual; i++) {
            Row row = sheet.getRow(i);
            for (Cell cell : row) {
                int childNesting = cell.getColumnIndex();

                if (!cell.getStringCellValue().isEmpty() && childNesting > parentNesting) {
                    toRemoveRows.add(cell.getRow());
                } else {
                    isSearchActual = false;
                }
            }
        }
        return toRemoveRows;
    }


}
