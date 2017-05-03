package com.company.icvretail.entity.catalogs;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.validation.constraints.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Lob;

@NamePattern("%s %s|number,description")
@Table(name = "ICVRETAIL_COUNTERPARTIES")
@Entity(name = "icvretail$Counterparties")
public class Counterparties extends StandardEntity {
    private static final long serialVersionUID = -943251568331621829L;

    @NotNull(message = "{msg://com.company.icvretail.entity/number can not empty}")
    @Column(name = "NUMBER_")
    protected Long number;

    @NotNull
    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "ADDRESS", length = 500)
    protected String address;

    @Column(name = "TELEPHONE")
    protected String telephone;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "IS_CLIENT")
    protected Boolean isClient;

    @Column(name = "IS_SUPPLIER")
    protected Boolean isSupplier;

    @Lob
    @Column(name = "NOTE")
    protected String note;

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setIsClient(Boolean isClient) {
        this.isClient = isClient;
    }

    public Boolean getIsClient() {
        return isClient;
    }

    public void setIsSupplier(Boolean isSupplier) {
        this.isSupplier = isSupplier;
    }

    public Boolean getIsSupplier() {
        return isSupplier;
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