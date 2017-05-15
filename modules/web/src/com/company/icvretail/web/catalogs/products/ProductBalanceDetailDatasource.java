/*
 * Copyright@ by IconViet
 */

package com.company.icvretail.web.catalogs.products;

import com.company.icvretail.entity.catalogs.ProductBalanceDetail;
import com.company.icvretail.entity.catalogs.Products;
import com.company.icvretail.service.ProductBalanceService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.data.impl.CollectionDatasourceImpl;

import java.util.*;

/**
 * Created by ntha on 5/15/2017.
 */
public class ProductBalanceDetailDatasource extends CollectionDatasourceImpl<ProductBalanceDetail, UUID> {
    private ProductBalanceService service = AppBeans.get(ProductBalanceService.NAME);

    @Override
    protected void loadData(Map<String, Object> params) {
        data.clear();

        dataLoadError = null;

        try {
            Date currentDate = (Date) params.get("currentDate");
            Products product = (Products) params.get("product");
        if (product !=null){
         List<ProductBalanceDetail> list = service.getTurnoverByProducts(currentDate,product);
            for (ProductBalanceDetail row : list) {
                data.put(row.getId(), row);
            }}
        } catch (Exception e) {
            dataLoadError = e;
        }

    }
}
