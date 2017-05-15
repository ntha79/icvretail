/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.service;


import com.company.icvretail.entity.catalogs.ProductBalanceDetail;
import com.company.icvretail.entity.catalogs.Products;
import com.company.icvretail.entity.catalogs.Warehouses;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ProductBalanceService {
    String NAME = "icvretail_ProductBalanceService";
    List<ProductBalanceDetail> getTurnoverByProducts(Date currentDate,Products product);
}