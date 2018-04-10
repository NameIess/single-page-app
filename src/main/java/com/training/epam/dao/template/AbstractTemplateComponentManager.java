package com.training.epam.dao.template;

import com.training.epam.dao.exception.DaoException;
import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.XLSBusinessException;
import com.training.epam.util.FileWriter;
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

    @Autowired
    public AbstractTemplateComponentManager(FileBrowser fileBrowser, FileWriter fileSaver) {
        this.fileBrowser = fileBrowser;
        this.fileSaver = fileSaver;
    }

    protected abstract void execute(Sheet sheet, JsonDto jsonDto);

    public void updateFile(String fileName, JsonDto jsonDto) throws DaoException {
        File file = fileBrowser.getFile(fileName);

        try (InputStream inputStream = new FileInputStream(file)) {
            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                for (Sheet sheet : workbook) {
                    execute(sheet, jsonDto);
                }

                fileSaver.saveFile(file, workbook);
            }

        } catch (InvalidFormatException | XLSBusinessException | IOException e) {
            throw new DaoException("Exception within updateFile(): " + e.getMessage(), e);
        }
    }

    protected Cell findCellByName(Sheet sheet, String cellName) {
        Cell keyCell = null;
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (!cell.getStringCellValue().isEmpty() && cell.getCellTypeEnum() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.equals(cellName)) {
                        keyCell = cell;      // NEED A WAY TO BREAK
                    }
                }
            }
        }
        return keyCell;
    }
}
