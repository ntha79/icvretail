package com.company.icvretail.entity.registers;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.company.icvretail.entity.catalogs.Products;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.company.icvretail.entity.enums.RecordType;
import com.company.icvretail.entity.catalogs.Warehouses;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import javax.persistence.Lob;

@Table(name = "ICVRETAIL_WAREHOUSE_BALANCE")
@Entity(name = "icvretail$WarehouseBalance")
public class WarehouseBalance extends StandardEntity {
    private static final long serialVersionUID = 4802036013937161880L;

    @Column(name = "RECORDER")
    protected UUID recorder;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PERIOD")
    protected Date period;

    @Column(name = "RECORD_TYPE")
    protected String recordType;

    @Column(name = "NUM_LINE")
    protected Integer numLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    protected Warehouses warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    protected Products product;

    @Column(name = "QUANTITY")
    protected BigDecimal quantity;

    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    public Integer getNumLine() {
        return numLine;
    }

    public void setNumLine(Integer numLine) {
        this.numLine = numLine;
    }


    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void setRecorder(UUID recorder) {
        this.recorder = recorder;
    }

    public UUID getRecorder() {
        return recorder;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Date getPeriod() {
        return period;
    }

    public void setWarehouse(Warehouses warehouse) {
        this.warehouse = warehouse;
    }

    public Warehouses getWarehouse() {
        return warehouse;
    }


    public void setRecordType(RecordType recordType) {
        this.recordType = recordType == null ? null : recordType.getId();
    }

    public RecordType getRecordType() {
        return recordType == null ? null : RecordType.fromId(recordType);
    }


    public void setProduct(Products product) {
        this.product = product;
    }

    public Products getProduct() {
        return product;
    }


}