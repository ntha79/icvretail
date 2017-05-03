package com.company.icvretail.entity.documents.invoicedoc;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.company.icvretail.entity.catalogs.Products;
import java.math.BigDecimal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;

@Table(name = "ICVRETAIL_INVOICE_PRODUCT_LINES")
@Entity(name = "icvretail$InvoiceProductLines")
public class InvoiceProductLines extends StandardEntity {
    private static final long serialVersionUID = -5185789245127339147L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVOICES_ID")
    protected Invoices invoices;

    @Column(name = "NUM_LINE")
    protected Integer numLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    protected Products product;

    @Column(name = "QUANTITY")
    protected BigDecimal quantity;

    @Column(name = "PRICE")
    protected BigDecimal price;

    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @Column(name = "DISCOUNT", precision = 2, scale = 0)
    protected BigDecimal discount;

    @Column(name = "AMOUNT_DISCOUNT")
    protected BigDecimal amountDiscount;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    public Invoices getInvoices() {
        return invoices;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setAmountDiscount(BigDecimal amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public BigDecimal getAmountDiscount() {
        return amountDiscount;
    }


    public void setProduct(Products product) {
        this.product = product;
    }

    public Products getProduct() {
        return product;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }


    public void setNumLine(Integer numLine) {
        this.numLine = numLine;
    }

    public Integer getNumLine() {
        return numLine;
    }


}