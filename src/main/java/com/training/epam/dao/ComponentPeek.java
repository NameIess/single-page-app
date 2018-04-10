//package com.training.epam.dao;
//
//import com.training.epam.dao.exception.DaoException;
//import com.training.epam.entity.Composite;
//import com.training.epam.entity.dto.request.JsonDto;
//import com.training.epam.factory.CompositeFactory;
//import com.training.epam.util.FileBrowser;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component("componentPeek")
//public class ComponentPeek {
//    private CompositeFactory compositeFactory;
//    private FileBrowser fileBrowser;
//
//    @Autowired
//    public ComponentPeek(CompositeFactory compositeFactory, FileBrowser fileBrowser) {
//        this.compositeFactory = compositeFactory;
//        this.fileBrowser = fileBrowser;
//    }
//
//    private Workbook getDataFromFile(String fileName) throws DaoException {
//        File file = fileBrowser.getFile(fileName);
//        try (Workbook workbook = WorkbookFactory.create(file)) {
//            return workbook;
//
//        } catch (InvalidFormatException | IOException e) {
//            throw new DaoException("Exception within getDataFromFile(): " + e.getMessage(), e);
//        }
//    }
//
//    public Composite findByName(String fileName, JsonDto jsonDto) throws DaoException {
//        Workbook workbook = getDataFromFile(fileName);
//        Composite composite = compositeFactory.create();
//        for (Sheet sheet : workbook) {
//            String name = jsonDto.getId();
//            Composite current = findNodeByName(sheet, name);
//            composite.addComponent(current);
//        }
//
//        return composite;
//    }
//
//    private Composite findNodeByName(Sheet sheet, String cellName) {
//        Composite composite = compositeFactory.create();
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                String cellValue = cell.getStringCellValue();
//                if (!cellValue.isEmpty() && cell.getCellTypeEnum() == CellType.STRING && StringUtils.startsWithIgnoreCase(cellValue, cellName)) {
//                    Composite childNodes = findChildNodes(sheet, row, cell);
//                    composite.addComponent(childNodes);
//                }
//            }
//        }
//        return composite;
//    }
//
//    private Composite findChildNodes(Sheet sheet, Row nodeRow, Cell nodeCell) {
//        int rowNum = nodeRow.getRowNum();
//        int nodeNesting = nodeCell.getColumnIndex();
//        Map<Integer, Composite> parentMap = new HashMap<>();
//
//        Composite composite = compositeFactory.create();
//        Composite current = null;
//        boolean isSearchActual = true;
//        int parentCount = 0;
//        for (int i = rowNum; i < sheet.getPhysicalNumberOfRows() && isSearchActual; i++) {
//            Row row = sheet.getRow(i);
//            for (int j = 0; j < row.getLastCellNum() && isSearchActual; j++) {
//                Cell cell = row.getCell(j);
////            for (Cell cell : row) {
//                String cellValue = cell.getStringCellValue();
//                if (!cellValue.isEmpty() && cell.getCellTypeEnum() == CellType.STRING) {
//                    int currentNesting = cell.getColumnIndex();
//
//                    if (currentNesting == nodeNesting) {
//                        current = compositeFactory.addChild(composite, cell);
//                        parentCount ++;
//
//                    } else if (currentNesting > nodeNesting) {
//                        current = compositeFactory.addChild(current, cell);
//
//                    } else if (currentNesting <= nodeNesting) {
//                        if (parentCount != 0 && currentNesting <= nodeCell.getColumnIndex()) {
//                            isSearchActual = false;
//                        } else {
//                            int parentIndex = currentNesting - 1;
//                            Composite parent = parentMap.get(parentIndex);
//                            current = compositeFactory.addChild(parent, cell);
//                        }
//                    }
//
//                    parentMap.put(currentNesting, current);
//                    nodeNesting = currentNesting;
//                }
//            }
//        }
//        return composite;
//    }
//}
