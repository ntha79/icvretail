package com.company.icvretail.entity.enums;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum RecordType implements EnumClass<String> {

    Rreceipt("Receipt"),
    Expense("Expense");

    private String id;

    RecordType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static RecordType fromId(String id) {
        for (RecordType at : RecordType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}