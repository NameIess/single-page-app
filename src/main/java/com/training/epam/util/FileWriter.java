package com.training.epam.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component("xlsFileWriter")
public class FileWriter {

    public void saveFile(File file, Workbook workbook) throws XLSBusinessException {
        try (FileOutputStream fileOut = new FileOutputStream(file)) {

            workbook.write(fileOut);

        } catch (IOException e) {
            throw new XLSBusinessException("Exception within saveFile(): " + e.getMessage(), e);
        }
    }
}
