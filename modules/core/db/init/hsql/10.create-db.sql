-- begin ICVRETAIL_PRODUCTS
create table ICVRETAIL_PRODUCTS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION varchar(255),
    PRICE decimal(19, 2),
    UNIT varchar(50),
    BARCODE varchar(255),
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_PRODUCTS
-- begin ICVRETAIL_COUNTERPARTIES
create table ICVRETAIL_COUNTERPARTIES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION varchar(255),
    ADDRESS varchar(500),
    TELEPHONE varchar(255),
    EMAIL varchar(255),
    IS_CLIENT boolean,
    IS_SUPPLIER boolean,
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_COUNTERPARTIES
-- begin ICVRETAIL_WAREHOUSES
create table ICVRETAIL_WAREHOUSES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION varchar(255),
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_WAREHOUSES
-- begin ICVRETAIL_INVOICES
create table ICVRETAIL_INVOICES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ bigint,
    PREFIX_NUMBER varchar(10),
    DATE_ timestamp,
    DESCRIPTION varchar(500),
    COUNTERPARTY_ID varchar(36),
    WAREHOUSE_ID varchar(36),
    NOTE longvarchar,
    TOTAL_AMOUNT decimal(19, 2),
    --
    primary key (ID)
)^
-- end ICVRETAIL_INVOICES
-- begin ICVRETAIL_PURCHASES
create table ICVRETAIL_PURCHASES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    PREFIX_NUMBER varchar(10),
    DESCRIPTION varchar(500),
    DATE_ timestamp,
    COUNTERPARTY_ID varchar(36),
    WAREHOUSE_ID varchar(36),
    NOTE varchar(255),
    TOTAL_AMOUNT decimal(19, 2),
    --
    primary key (ID)
)^
-- end ICVRETAIL_PURCHASES
-- begin ICVRETAIL_INVOICE_PRODUCT_LINES
create table ICVRETAIL_INVOICE_PRODUCT_LINES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    INVOICES_ID varchar(36),
    NUM_LINE integer,
    PRODUCT_ID varchar(36),
    QUANTITY decimal(19, 2),
    PRICE decimal(19, 2),
    AMOUNT decimal(19, 2),
    DISCOUNT decimal(2),
    AMOUNT_DISCOUNT decimal(19, 2),
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_INVOICE_PRODUCT_LINES
-- begin ICVRETAIL_PURCHASE_PRODUCT_LINES
create table ICVRETAIL_PURCHASE_PRODUCT_LINES (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUM_LINE integer,
    PRODUCT_ID varchar(36),
    QUANTITY decimal(19, 2),
    PRICE decimal(19, 2),
    AMOUNT decimal(19, 2),
    DISCOUNT decimal(2),
    AMOUNT_DISCOUNT decimal(19, 2),
    PURCHASES_ID varchar(36),
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_PURCHASE_PRODUCT_LINES
-- begin ICVRETAIL_WAREHOUSE_BALANCE
create table ICVRETAIL_WAREHOUSE_BALANCE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    RECORDER varchar(36),
    PERIOD timestamp,
    RECORD_TYPE varchar(50),
    NUM_LINE integer,
    WAREHOUSE_ID varchar(36),
    PRODUCT_ID varchar(36),
    QUANTITY decimal(19, 2),
    AMOUNT decimal(19, 2),
    NOTE longvarchar,
    --
    primary key (ID)
)^
-- end ICVRETAIL_WAREHOUSE_BALANCE
