package com.company.icvretail.web.catalogs.warehouses;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.catalogs.Warehouses;

import javax.inject.Inject;

public class WarehousesEdit extends AbstractEditor<Warehouses> {
    @Inject
    private UniqueNumbersService unService;
    @Override
    protected void initNewItem(Warehouses item) {
        item.setNumber(unService.getNextNumber("WarehousesNumber"));
    }
}