package com.company.icvretail.web.catalogs.counterparties;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.catalogs.Counterparties;

import javax.inject.Inject;
import java.util.Date;

public class CounterpartiesEdit extends AbstractEditor<Counterparties> {
    @Inject
    private UniqueNumbersService unService;
    @Override
    protected void initNewItem(Counterparties item) {
        item.setNumber(unService.getNextNumber("CounterpartiesNumber"));
     }
}