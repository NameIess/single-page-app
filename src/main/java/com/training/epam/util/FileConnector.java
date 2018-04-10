package com.training.epam.util;

import org.apache.poi.ss.usermodel.Workbook;

public interface FileConnector {
    Workbook readFile(String fileName);
}
