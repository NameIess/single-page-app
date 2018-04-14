package com.training.epam.dao.template.impl;

import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import com.training.epam.entity.validator.Verifiable;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileConnector;
import com.training.epam.util.FileWriter;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("componentUpdater")
public class ComponentUpdater extends AbstractTemplateComponentManager {

    public ComponentUpdater(FileBrowser fileBrowser, FileWriter fileSaver, Verifiable<Cell> cellValidator) {
        super(fileBrowser, fileSaver, cellValidator);
    }

    public boolean execute(Sheet sheet, JsonDto jsonDto) {
        UpdatingCriteria updateCriteria = (UpdatingCriteria) jsonDto;
        String currentName = updateCriteria.getId();
        String updatedName = updateCriteria.getUpdatedName();

        Cell cellToUpdate = findCellByName(sheet, currentName);

        boolean isUpdated = false;
        if (cellToUpdate != null) {
            cellToUpdate.setCellValue(updatedName);
            isUpdated = true;
        }

        return isUpdated;
    }

}
