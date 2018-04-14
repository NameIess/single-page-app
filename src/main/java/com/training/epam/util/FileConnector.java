package com.training.epam.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public interface FileConnector {
    Workbook readFile(String fileName) throws IOException, InvalidFormatException, XLSBusinessException;
}
