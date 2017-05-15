/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.entity.catalogs;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.cuba.core.entity.AbstractNotPersistentEntity;
import com.haulmont.chile.core.annotations.MetaProperty;
import java.math.BigDecimal;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s  %s|wareHouse,balance")
@MetaClass(name = "icvretail$ProductBalanceDetail")
public class ProductBalanceDetail extends AbstractNotPersistentEntity {
    private static final long serialVersionUID = 4904300478497646531L;

    @MetaProperty
    protected Warehouses wareHouse;

    @MetaProperty
    protected BigDecimal balance;

    public void setWareHouse(Warehouses wareHouse) {
        this.wareHouse = wareHouse;
    }

    public Warehouses getWareHouse() {
        return wareHouse;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }


}