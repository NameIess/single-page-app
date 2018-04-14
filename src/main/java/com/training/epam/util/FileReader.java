package com.training.epam.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component("xlsFileReader")
public class FileReader implements FileConnector {
    private FileBrowser fileBrowser;


    @Autowired
    public FileReader(FileBrowser fileBrowser) {
        this.fileBrowser = fileBrowser;
    }

    public Workbook readFile(String fileName) throws IOException, InvalidFormatException, XLSBusinessException {
        File file = fileBrowser.getFile(fileName);
        Workbook workbook = WorkbookFactory.create(file);
        return workbook;
    }
}
