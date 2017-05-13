package com.company.icvretail.entity.documents.invoicedoc;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.icvretail.service.GenerateDocumentNumberService;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;


import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.company.icvretail.entity.catalogs.Counterparties;
import com.company.icvretail.entity.catalogs.Warehouses;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@Listeners("icvretail_InvoicesListener")
@NamePattern("%s %s %s|description,date,number")
@Table(name = "ICVRETAIL_INVOICES")
@Entity(name = "icvretail$Invoices")
public class Invoices extends StandardEntity {
    private static final long serialVersionUID = 7230911769769975134L;

    @NotNull
    @Column(name = "NUMBER_")
    protected Long number;

    @Column(name = "PREFIX_NUMBER", length = 10)
    protected String prefixNumber;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_")
    protected Date date;

    @Column(name = "DESCRIPTION", length = 500)
    protected String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTERPARTY_ID")
    protected Counterparties counterparty;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    protected Warehouses warehouse;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "invoices")
    protected List<InvoiceProductLines> productLines;

    @Column(name = "TOTAL_AMOUNT")
    protected BigDecimal totalAmount;

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setProductLines(List<InvoiceProductLines> productLines) {
        this.productLines = productLines;
    }

    public List<InvoiceProductLines> getProductLines() {
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



    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setPrefixNumber(String prefixNumber) {
        this.prefixNumber = prefixNumber;
    }

    public String getPrefixNumber() {
        return prefixNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    @PostConstruct
    protected void init() {
        Long numb= AppBeans.get(GenerateDocumentNumberService.class).getNextDocumentNumber("InvoicesNumber");
        setNumber(numb);
    }
}