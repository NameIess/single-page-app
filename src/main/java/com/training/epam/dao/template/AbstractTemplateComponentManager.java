package com.training.epam.dao.template;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileConnector;
import com.training.epam.util.FileWriter;
import com.training.epam.util.XLSBusinessException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractTemplateComponentManager {
    private FileBrowser fileBrowser;
    private FileWriter fileSaver;
    private Verifiable<Cell> cellVerifiable;

    @Autowired
    public AbstractTemplateComponentManager(
            FileBrowser fileBrowser, FileWriter fileSaver, Verifiable<Cell> cellVerifiable) {
        this.fileBrowser = fileBrowser;
        this.fileSaver = fileSaver;
        this.cellVerifiable = cellVerifiable;
    }

    protected abstract boolean execute(Sheet sheet, JsonDto jsonDto);

    public boolean updateFile(String fileName, JsonDto jsonDto) throws DaoException {
        File file = fileBrowser.getFile(fileName);

        boolean hasSuccess = false;
        try (InputStream inputStream = new FileInputStream(file)) {
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                for (Sheet sheet : workbook) {
                    hasSuccess = execute(sheet, jsonDto);
                }

                if (hasSuccess) {
                    fileSaver.saveFile(file, workbook);
                }
            }

            return hasSuccess;

        } catch (InvalidFormatException | XLSBusinessException | IOException e) {
            throw new DaoException("Exception within updateFile(): " + e.getMessage(), e);
        }
    }

    protected Cell findCellByName(Sheet sheet, String cellName) {
        Cell keyCell = null;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cellVerifiable.isValid(cell)) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.equals(cellName)) {
                        keyCell = cell;
                    }
                }
            }
        }
        return keyCell;
    }

    protected boolean isCellValid(Cell cell) {
        boolean isValid = cellVerifiable.isValid(cell);
        return isValid;
    }
}
