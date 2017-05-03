package com.company.icvretail.web.catalogs.products;

import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.catalogs.Products;

import javax.inject.Inject;

public class ProductsEdit extends AbstractEditor<Products> {
    @Inject
    private UniqueNumbersService unService;
    @Override
    protected void initNewItem(Products item) {
        item.setNumber(unService.getNextNumber("ProductsNumber"));
    }
}