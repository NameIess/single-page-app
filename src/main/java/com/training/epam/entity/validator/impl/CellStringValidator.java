package com.training.epam.entity.validator.impl;

import com.training.epam.entity.validator.Verifiable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

@Component("cellValidator")
public class CellStringValidator implements Verifiable<Cell> {

    @Override
    public boolean isValid(Cell cell) {
        boolean isValid = false;

        if (cell != null && cell.getCellTypeEnum() == CellType.STRING && !cell.getStringCellValue().isEmpty()) {
            isValid = true;
        }

        return isValid;
    }
}
