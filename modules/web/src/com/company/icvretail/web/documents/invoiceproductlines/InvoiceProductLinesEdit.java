package com.company.icvretail.web.documents.invoiceproductlines;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.documents.invoicedoc.InvoiceProductLines;
import com.haulmont.cuba.gui.components.TextField;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Map;

public class InvoiceProductLinesEdit extends AbstractEditor<InvoiceProductLines> {
    @Named("fieldGroup.price")
    private TextField priceField;

    @Named("fieldGroup.quantity")
    private TextField quantityField;

    @Named("fieldGroup.discount")
    private TextField discountField;

    @Override
    public void init(Map<String, Object> params) {
        priceField.addValueChangeListener(this::calculateAmount);
        quantityField.addValueChangeListener(this::calculateAmount);
        discountField.addValueChangeListener(this::calculateAmount);
    }

    private void calculateAmount(ValueChangeEvent valueChangeEvent) {
        if (getItem() != null && getItem().getPrice() != null && getItem().getQuantity() != null) {

            BigDecimal amount =getItem().getPrice()
                                .multiply( getItem().getQuantity());
            BigDecimal amountDiscount=BigDecimal.ZERO;
            if(getItem().getDiscount()!=null){
                amountDiscount = (amount
                                .multiply(getItem().getDiscount())).divide(BigDecimal.valueOf(100));
            }
            getItem().setAmountDiscount(amountDiscount);
            getItem().setAmount(amount.subtract(amountDiscount));

        } else
            getItem().setAmount(null);
    }

}

