/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.service;

import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(GenerateDocumentNumberService.NAME)
public class GenerateDocumentNumberServiceBean implements GenerateDocumentNumberService {
    @Inject
    private UniqueNumbersAPI uniqueNumbersAPI;

    @Override
    public Long getNextDocumentNumber(String domainName) {
        Long num=1L;
        num = uniqueNumbersAPI.getCurrentNumber(domainName);

        return num>1?num+1:num;
    }

}