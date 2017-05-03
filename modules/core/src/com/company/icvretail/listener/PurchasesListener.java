package com.company.icvretail.listener;

import com.company.icvretail.entity.documents.purchasedoc.PurchaseProductLines;
import com.company.icvretail.entity.enums.RecordType;
import com.company.icvretail.entity.registers.WarehouseBalance;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import java.sql.Connection;
import com.company.icvretail.entity.documents.purchasedoc.Purchases;
import com.haulmont.cuba.core.listener.AfterUpdateEntityListener;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Component("icvretail_PurchasesListener")
public class PurchasesListener implements AfterInsertEntityListener<Purchases>, AfterUpdateEntityListener<Purchases> {


    @Inject
    private Persistence persistence;

    @Inject
    private Metadata metadata;

    @Override
    public void onAfterInsert(Purchases entity, Connection connection) {
        deleteBalance(entity);
        updateBalance(entity);
    }


    @Override
    public void onAfterUpdate(Purchases entity, Connection connection) {
        deleteBalance(entity);
        updateBalance(entity);
    }

    private void insertTransaction(Purchases entity) {
        EntityManager em = persistence.getEntityManager();
        for (PurchaseProductLines purchaseProductLines : entity.getProductLines()){
            WarehouseBalance warehouseBalance =  metadata.create(WarehouseBalance.class);
            warehouseBalance.setRecordType(RecordType.Rreceipt);
            warehouseBalance.setRecorder(entity.getId());
            warehouseBalance.setPeriod(entity.getDate());
            warehouseBalance.setWarehouse(entity.getWarehouse());
            warehouseBalance.setNumLine(purchaseProductLines.getNumLine());
            warehouseBalance.setProduct(purchaseProductLines.getProduct());
            warehouseBalance.setAmount(purchaseProductLines.getAmount());
            warehouseBalance.setQuantity(purchaseProductLines.getQuantity());
            warehouseBalance.setNote(purchaseProductLines.getNote());
            em.persist(warehouseBalance);
        }
    }

    /*Process update all record ProductLine in documents */
    @Transactional
    public void updateBalance(Purchases entity) {

        try (Transaction tx = persistence.createTransaction()) {

            for (PurchaseProductLines purchaseProductLines : entity.getProductLines()){

                WarehouseBalance warehouseBalance =  metadata.create(WarehouseBalance.class);
                warehouseBalance.setRecordType(RecordType.Rreceipt);
                warehouseBalance.setRecorder(entity.getId());
                warehouseBalance.setPeriod(entity.getDate());
                warehouseBalance.setWarehouse(entity.getWarehouse());
                warehouseBalance.setNumLine(purchaseProductLines.getNumLine());
                warehouseBalance.setProduct(purchaseProductLines.getProduct());
                warehouseBalance.setAmount(purchaseProductLines.getAmount());
                warehouseBalance.setQuantity(purchaseProductLines.getQuantity());
                warehouseBalance.setNote(purchaseProductLines.getNote());
                persistence.getEntityManager().persist(warehouseBalance);
            }
            tx.commit();
        }
    }

    /* Process delete all related record in WarehouseBalance Register*/
    @Transactional
    public void deleteBalance(Purchases entity){

        try (Transaction tx = persistence.createTransaction()) {
            Query query = persistence.getEntityManager().createQuery(
                    "delete from icvretail$WarehouseBalance c where c.recorder = :idDocument");
            query.setParameter("idDocument", entity.getId());
            query.executeUpdate();
            tx.commit();
        }
    }
}