package com.training.epam.dao.template.impl;

import com.training.epam.dao.template.AbstractTemplateComponentManager;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.SearchingCriteria;
import com.training.epam.factory.CompositeFactory;
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

    public ComponentEraser(FileBrowser fileBrowser, FileWriter fileSaver, CompositeFactory compositeFactory) {
        super(fileBrowser, fileSaver, compositeFactory);
    }

    @Override
    protected void execute(Sheet sheet, JsonDto jsonDto) {
        SearchingCriteria searchingCriteria = (SearchingCriteria) jsonDto;
        String cellName = searchingCriteria.getId();

        Cell cellToRemove = findCellByName(sheet, cellName);

        List<Row> toRemoveRows = findRowsToRemove(sheet, cellToRemove);
        removeRows(sheet, toRemoveRows);
    }

    private void removeRows(Sheet sheet, List<Row> rowList) {
        for (Row row : rowList) {
            sheet.removeRow(row);
        }
    }

    private List<Row> findRowsToRemove(Sheet sheet, Cell parentCell) {
        Row parentRow = parentCell.getRow();
        int rowNum = parentRow.getRowNum();
        int parentNestingLevel = parentCell.getColumnIndex();

        List<Row> toRemoveRows = new ArrayList<>();

        boolean isSearchActual = true;
        for (int i = rowNum; i < sheet.getPhysicalNumberOfRows() && isSearchActual; i++) {
            Row row = sheet.getRow(i);
            for (Cell cell : row) {
                int childNestingLevel = cell.getColumnIndex();

                if (!cell.getStringCellValue().isEmpty() && childNestingLevel >= parentNestingLevel) {
                    toRemoveRows.add(cell.getRow());
                } else {
                    isSearchActual = false;
                }
            }
        }
        return toRemoveRows;
    }


}
