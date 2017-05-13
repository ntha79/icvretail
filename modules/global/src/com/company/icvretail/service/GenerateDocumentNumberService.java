/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.service;


public interface GenerateDocumentNumberService {
    String NAME = "icvretail_GenerateDocumentNumberService";
    Long getNextDocumentNumber(String domainName);
}