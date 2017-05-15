package com.company.icvretail.web.documents.invoice;

import com.company.icvretail.entity.documents.invoicedoc.InvoiceProductLines;
import com.company.icvretail.entity.documents.invoicedoc.Invoices;
import com.company.icvretail.entity.documents.purchasedoc.PurchaseProductLines;
import com.company.icvretail.entity.documents.purchasedoc.Purchases;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoicesBrowse extends AbstractLookup {

    @Inject
    private Table<Invoices> invoicesesTable;

    @Inject
    private Metadata metadata;
    public void onBaseCreatePurchase(Component source) {
        Invoices invoice = invoicesesTable.getSingleSelected();

        if(invoice !=null){
            Purchases purchase= metadata.create(Purchases.class);
            purchase.setCounterparty(invoice.getCounterparty());
            purchase.setWarehouse(invoice.getWarehouse());
            List<PurchaseProductLines> list= new ArrayList<>();
            Integer numLine=0;
            for(InvoiceProductLines row : invoice.getProductLines()){
                numLine=numLine+1;
                PurchaseProductLines purchaseLine = metadata.create(PurchaseProductLines.class);
                purchaseLine.setPurchases(purchase);
                purchaseLine.setProduct(row.getProduct());
                purchaseLine.setQuantity(row.getQuantity());
                purchaseLine.setDiscount(BigDecimal.ZERO);
                purchaseLine.setPrice(row.getPrice());
                purchaseLine.setNumLine(numLine);
                list.add(purchaseLine);
            }
            purchase.setProductLines(list);
            openEditor("icvretail$Purchases.edit",purchase, WindowManager.OpenType.NEW_WINDOW);
        }
    }

    public void onCopy(Component source) {
        Invoices invoiceCurrent = invoicesesTable.getSingleSelected();
        if(invoiceCurrent !=null){
            Invoices invoiceNew= metadata.create(Invoices.class);
            invoiceNew.setCounterparty(invoiceCurrent.getCounterparty());
            invoiceNew.setWarehouse(invoiceCurrent.getWarehouse());
            List<InvoiceProductLines> list= new ArrayList<>();
            Integer numLine=0;
            for(InvoiceProductLines row : invoiceCurrent.getProductLines()){
                numLine=numLine+1;
                InvoiceProductLines invoiceLine = metadata.create(InvoiceProductLines.class);
                invoiceLine.setInvoices(invoiceNew);
                invoiceLine.setProduct(row.getProduct());
                invoiceLine.setQuantity(row.getQuantity());
                invoiceLine.setDiscount(BigDecimal.ZERO);
                invoiceLine.setPrice(row.getPrice());
                invoiceLine.setNumLine(numLine);
                list.add(invoiceLine);
            }
            invoiceNew.setProductLines(list);
            openEditor("icvretail$Invoices.edit",invoiceNew,WindowManager.OpenType.NEW_WINDOW);
        }

    }
}