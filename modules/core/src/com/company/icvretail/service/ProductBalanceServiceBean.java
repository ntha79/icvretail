/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.service;

import com.company.icvretail.entity.catalogs.ProductBalanceDetail;
import com.company.icvretail.entity.catalogs.Products;
import com.company.icvretail.entity.catalogs.Warehouses;
import com.company.icvretail.entity.registers.WarehouseBalance;
import com.haulmont.cuba.core.*;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.ValueLoadContext;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service(ProductBalanceService.NAME)
public class ProductBalanceServiceBean implements ProductBalanceService {
    private Log log = LogFactory.getLog(getClass());
    @Inject
    protected Persistence persistence;
    @Inject
    private DataManager dataManager;
    @Override
    @Transactional
    public List<ProductBalanceDetail> getTurnoverByProducts(Date currentDate, Products product) {

        List<ProductBalanceDetail> mlist = new ArrayList<>();;
        /*
        EntityManager em = persistence.getEntityManager();
        String catQueryString = "select o.warehouse,o.quantity from icvretail$WarehouseBalance o " +
                "where o.period <= :currentDate " +
                " and o.product.id = :productId " ;//+
               // "group by o.warehouse ";
        TypedQuery<WarehouseBalance> catQuery = em.createQuery(catQueryString, WarehouseBalance.class);

        catQuery.setParameter("currentDate", currentDate)
                .setParameter("productId", product.getId());

        for (WarehouseBalance item : catQuery.getResultList()) {
            ProductBalanceDetail row = new ProductBalanceDetail();
            row.setWareHouse(item.getWarehouse());
            row.setBalance(item.getQuantity());
            list.add(row);
        }*/

        ValueLoadContext context = ValueLoadContext.create()
                .setQuery(ValueLoadContext.createQuery(
                        "select o.warehouse,sum(o.quantity)as balance from icvretail$WarehouseBalance o " +
                                "where o.period <= :currentDate " +
                                " and o.product.id = :productId "+
                                "group by o.warehouse")
                        .setParameter("currentDate", currentDate)
                        .setParameter("productId", product.getId()))
                .addProperty("warehouse")
                .addProperty("balance");
        List<KeyValueEntity> list = dataManager.loadValues(context);

        for (KeyValueEntity k:list){
            ProductBalanceDetail row = new ProductBalanceDetail();
            row.setWareHouse(k.getValue("warehouse"));
            row.setBalance(k.getValue("balance"));
            mlist.add(row);
            
        }
        
        
        return  mlist ;
    }
}