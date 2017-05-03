package com.company.icvretail.entity.catalogs;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;

@NamePattern("%s %s|description,number")
@Table(name = "ICVRETAIL_PRODUCTS")
@Entity(name = "icvretail$Products")
public class Products extends StandardEntity {
    private static final long serialVersionUID = 738741641033001214L;

    @NotNull(message = "{msg://com.company.icvretail.entity/number can not empty}")
    @Column(name = "NUMBER_", unique = true)
    protected Long number;

    @NotNull
    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "PRICE")
    protected BigDecimal price;

    @Column(name = "UNIT", length = 50)
    protected String unit;

    @Column(name = "BARCODE")
    protected String barcode;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public void setNumber(Long number) {
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }


}