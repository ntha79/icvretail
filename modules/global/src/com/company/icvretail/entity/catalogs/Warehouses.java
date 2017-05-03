package com.company.icvretail.entity.catalogs;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;

@NamePattern("%s %s|number,description")
@Table(name = "ICVRETAIL_WAREHOUSES")
@Entity(name = "icvretail$Warehouses")
public class Warehouses extends StandardEntity {
    private static final long serialVersionUID = 749814712637885432L;

    @NotNull(message = "{msg://com.company.icvretail.entity/number can not empty}")
    @Column(name = "NUMBER_")
    protected Long number;

    @NotNull
    @Column(name = "DESCRIPTION")
    protected String description;

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


}