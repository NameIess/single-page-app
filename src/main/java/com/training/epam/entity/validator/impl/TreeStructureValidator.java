package com.training.epam.entity.validator.impl;

import com.training.epam.entity.validator.Verifiable;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("treeStructure")
public class TreeStructureValidator implements Verifiable<Workbook> {
    private Verifiable<Cell> cellValidator;

    @Autowired
    public TreeStructureValidator(Verifiable<Cell> cellValidator) {
        this.cellValidator = cellValidator;
    }

    @Override
    public boolean isValid(Workbook workbook) {
        boolean isValid = true;
        boolean isActualSearch = true;
        for (int i = 0; isActualSearch && i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int j = 0; isActualSearch && j < sheet.getPhysicalNumberOfRows(); j++) {
                int nodeColumnIndex = 0;
                int nodeRowIndex = 0;
                Row row = sheet.getRow(j);
                for (int k = 0; isActualSearch && k < row.getPhysicalNumberOfCells(); k++) {
                    Cell cell = row.getCell(k);

                    int childColumnIndex = cell.getColumnIndex();
                    int childRowIndex = cell.getRowIndex();

                    if (cellValidator.isValid(cell)) {
                        if (childColumnIndex > nodeColumnIndex && childRowIndex == nodeRowIndex) {
                            isValid = false;
                            isActualSearch = false;
                        } else {
                            nodeRowIndex = childRowIndex;
                            nodeColumnIndex = childColumnIndex;
                        }
                    }
                }
            }
        }

        return isValid;
    }
}
