package com.training.epam.factory;

import com.training.epam.entity.Composite;
import org.apache.poi.ss.usermodel.Cell;

@org.springframework.stereotype.Component("compositeFactory")
public class CompositeFactory {

    public Composite addChild(Composite parent, Cell cell) {
        Composite child = new Composite();
        child.setName(cell.getStringCellValue());
        parent.addComponent(child);

        return child;
    }

    public Composite create() {
        return new Composite();
    }
}
