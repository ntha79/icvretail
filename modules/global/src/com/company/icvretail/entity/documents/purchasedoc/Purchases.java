package com.company.icvretail.entity.documents.purchasedoc;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.Column;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.company.icvretail.entity.catalogs.Counterparties;
import com.company.icvretail.entity.catalogs.Warehouses;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@Listeners("icvretail_PurchasesListener")
@Table(name = "ICVRETAIL_PURCHASES")
@Entity(name = "icvretail$Purchases")
public class Purchases extends StandardEntity {
    private static final long serialVersionUID = -5551534397623602353L;

    @NotNull
    @Column(name = "NUMBER_")
    protected Long number;

    @Column(name = "PREFIX_NUMBER", length = 10)
    protected String prefixNumber;

    @Column(name = "DESCRIPTION", length = 500)
    protected String description;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_")
    protected Date date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTERPARTY_ID")
    protected Counterparties counterparty;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    protected Warehouses warehouse;

    @Column(name = "NOTE")
    protected String note;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "purchases")
    protected List<PurchaseProductLines> productLines;

    @Column(name = "TOTAL_AMOUNT")
    protected BigDecimal totalAmount;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setProductLines(List<PurchaseProductLines> productLines) {
        this.productLines = productLines;
    }

    public List<PurchaseProductLines> getProductLines() {
        return productLines;
    }


    public Warehouses getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouses warehouse) {
        this.warehouse = warehouse;
    }


    public Counterparties getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparties counterparty) {
        this.counterparty = counterparty;
    }




    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public String getPrefixNumber() {
        return prefixNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }



}