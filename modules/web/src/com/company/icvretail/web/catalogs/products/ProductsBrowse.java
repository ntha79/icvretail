package com.company.icvretail.web.catalogs.products;

import com.company.icvretail.entity.catalogs.ProductBalanceDetail;
import com.company.icvretail.entity.catalogs.Products;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductsBrowse extends AbstractLookup {
    @Inject
    protected CollectionDatasource<ProductBalanceDetail, UUID> productBalanceDetailsDs;
    @Inject
    private Table<Products> productsesTable;
   @Inject
    private CollectionDatasource<Products,UUID> productsesDs;

    @Override
    public void init(Map<String, Object> params) {
      //  super.init(params);

        productsesTable.setItemClickAction(new AbstractAction("tableClickAction") {
            @Override
            public void actionPerform(Component component) {
               Products product = productsesTable.getSingleSelected();
                if (product != null ){
                    refreshproductBalanceDetailsDs(product);
                }
            }
        });

        productsesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                refreshproductBalanceDetailsDs(e.getItem());
            }
        });
    }
    private Map<String, Object> createDatasourceParams(Object currentDate, Object product) {
        Map<String, Object> params = new HashMap<>();
        params.put("product", product);
        params.put("currentDate", currentDate);
        return params;
    }
    private void refreshproductBalanceDetailsDs(Products product) {

        productBalanceDetailsDs.refresh(createDatasourceParams(new Date(), product));
    }

   
}