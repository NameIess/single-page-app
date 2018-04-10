package com.training.epam.dao.template.impl;

import com.training.epam.entity.dto.request.JsonDto;
import com.training.epam.entity.dto.request.UpdatingCriteria;
import com.training.epam.factory.CompositeFactory;
import com.training.epam.util.FileBrowser;
import com.training.epam.util.FileWriter;
import com.training.epam.dao.template.AbstractTemplateComponentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

@Component("componentUpdater")
public class ComponentUpdater extends AbstractTemplateComponentManager {
    private static final Logger Log = LogManager.getLogger(ComponentUpdater.class.getSimpleName());


    public ComponentUpdater(FileBrowser fileBrowser, FileWriter fileSaver, CompositeFactory compositeFactory) {
        super(fileBrowser, fileSaver, compositeFactory);
    }

    public void execute(Sheet sheet, JsonDto jsonDto) {
        UpdatingCriteria updateCriteria = (UpdatingCriteria) jsonDto;
        String currentName = updateCriteria.getId();
        String updatedName = updateCriteria.getUpdatedName();

        Cell cellToUpdate = findCellByName(sheet, currentName);
        cellToUpdate.setCellValue(updatedName);
    }

}
