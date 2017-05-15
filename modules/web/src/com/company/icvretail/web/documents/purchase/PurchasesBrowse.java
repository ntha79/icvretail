package com.company.icvretail.web.documents.purchase;

import com.company.icvretail.entity.documents.invoicedoc.InvoiceProductLines;
import com.company.icvretail.entity.documents.invoicedoc.Invoices;
import com.company.icvretail.entity.documents.purchasedoc.PurchaseProductLines;
import com.company.icvretail.entity.documents.purchasedoc.Purchases;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import com.haulmont.cuba.gui.components.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PurchasesBrowse extends AbstractLookup {

    @Inject
    private Table<Purchases> purchasesesTable;

    @Inject
    private Metadata metadata;


    public void onBaseCreateInvoice(Component source) {
        Purchases purchase = purchasesesTable.getSingleSelected();
        if(purchase !=null){
            Invoices invoice= metadata.create(Invoices.class);
            invoice.setCounterparty(purchase.getCounterparty());
            invoice.setWarehouse(purchase.getWarehouse());
            List<InvoiceProductLines> list= new ArrayList<>();
            Integer numLine=0;
            for(PurchaseProductLines row : purchase.getProductLines()){
                numLine=numLine+1;
                InvoiceProductLines invoiceLine = metadata.create(InvoiceProductLines.class);
                invoiceLine.setInvoices(invoice);
                invoiceLine.setProduct(row.getProduct());
                invoiceLine.setQuantity(row.getQuantity());
                invoiceLine.setDiscount(BigDecimal.ZERO);
                invoiceLine.setPrice(row.getPrice());
                invoiceLine.setNumLine(numLine);
                list.add(invoiceLine);
            }
            invoice.setProductLines(list);
        openEditor("icvretail$Invoices.edit",invoice,WindowManager.OpenType.NEW_WINDOW);
        }

    }

    public void onCopy(Component source) {
        Purchases purchaseCurrent = purchasesesTable.getSingleSelected();
        if(purchaseCurrent !=null){
            Purchases purchaseNew= metadata.create(Purchases.class);
            purchaseNew.setCounterparty(purchaseCurrent.getCounterparty());
            purchaseNew.setWarehouse(purchaseCurrent.getWarehouse());
            List<PurchaseProductLines> list= new ArrayList<>();
            Integer numLine=0;
            for(PurchaseProductLines row : purchaseCurrent.getProductLines()){
                numLine=numLine+1;
                PurchaseProductLines purchaseLine = metadata.create(PurchaseProductLines.class);
                purchaseLine.setPurchases(purchaseNew);
                purchaseLine.setProduct(row.getProduct());
                purchaseLine.setQuantity(row.getQuantity());
                purchaseLine.setDiscount(BigDecimal.ZERO);
                purchaseLine.setPrice(row.getPrice());
                purchaseLine.setNumLine(numLine);
                list.add(purchaseLine);
            }
            purchaseNew.setProductLines(list);
            openEditor("icvretail$Purchases.edit",purchaseNew,WindowManager.OpenType.NEW_WINDOW);
        }
    }
}