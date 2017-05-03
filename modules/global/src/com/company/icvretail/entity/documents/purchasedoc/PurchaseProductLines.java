package com.company.icvretail.entity.documents.purchasedoc;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;
import com.company.icvretail.entity.catalogs.Products;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.math.BigDecimal;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Table(name = "ICVRETAIL_PURCHASE_PRODUCT_LINES")
@Entity(name = "icvretail$PurchaseProductLines")
public class PurchaseProductLines extends StandardEntity {
    private static final long serialVersionUID = 8069848195617579383L;

    @Column(name = "NUM_LINE")
    protected Integer numLine;

    @NotNull
    @OnDelete(DeletePolicy.CASCADE)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASES_ID")
    protected Purchases purchases;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }

    public Purchases getPurchases() {
        return purchases;
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


    public void setNumLine(Integer numLine) {
        this.numLine = numLine;
    }

    public Integer getNumLine() {
        return numLine;
    }


}