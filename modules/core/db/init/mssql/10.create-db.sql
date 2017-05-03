-- begin ICVRETAIL_PRODUCTS
create table ICVRETAIL_PRODUCTS (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION nvarchar(255),
    PRICE decimal(19, 2),
    UNIT nvarchar(50),
    BARCODE nvarchar(255),
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_PRODUCTS
-- begin ICVRETAIL_COUNTERPARTIES
create table ICVRETAIL_COUNTERPARTIES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION nvarchar(255),
    ADDRESS nvarchar(500),
    TELEPHONE nvarchar(255),
    EMAIL nvarchar(255),
    IS_CLIENT tinyint,
    IS_SUPPLIER tinyint,
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_COUNTERPARTIES
-- begin ICVRETAIL_WAREHOUSES
create table ICVRETAIL_WAREHOUSES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    NUMBER_ bigint,
    DESCRIPTION nvarchar(255),
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_WAREHOUSES
-- begin ICVRETAIL_INVOICES
create table ICVRETAIL_INVOICES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    NUMBER_ bigint,
    PREFIX_NUMBER nvarchar(10),
    DATE_ datetime2,
    DESCRIPTION nvarchar(500),
    COUNTERPARTY_ID uniqueidentifier,
    WAREHOUSE_ID uniqueidentifier,
    NOTE nvarchar(max),
    TOTAL_AMOUNT decimal(19, 2),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_INVOICES
-- begin ICVRETAIL_PURCHASES
create table ICVRETAIL_PURCHASES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime,
    DELETED_BY nvarchar(50),
    --
    NUMBER_ bigint,
    PREFIX_NUMBER nvarchar(10),
    DESCRIPTION nvarchar(500),
    DATE_ datetime,
    COUNTERPARTY_ID uniqueidentifier,
    WAREHOUSE_ID uniqueidentifier,
    NOTE nvarchar(255),
    TOTAL_AMOUNT decimal(19, 2),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_PURCHASES
-- begin ICVRETAIL_INVOICE_PRODUCT_LINES
create table ICVRETAIL_INVOICE_PRODUCT_LINES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    INVOICES_ID uniqueidentifier,
    NUM_LINE integer,
    PRODUCT_ID uniqueidentifier,
    QUANTITY decimal(19, 2),
    PRICE decimal(19, 2),
    AMOUNT decimal(19, 2),
    DISCOUNT decimal(2),
    AMOUNT_DISCOUNT decimal(19, 2),
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_INVOICE_PRODUCT_LINES
-- begin ICVRETAIL_PURCHASE_PRODUCT_LINES
create table ICVRETAIL_PURCHASE_PRODUCT_LINES (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    NUM_LINE integer,
    PRODUCT_ID uniqueidentifier,
    QUANTITY decimal(19, 2),
    PRICE decimal(19, 2),
    AMOUNT decimal(19, 2),
    DISCOUNT decimal(2),
    AMOUNT_DISCOUNT decimal(19, 2),
    PURCHASES_ID uniqueidentifier,
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_PURCHASE_PRODUCT_LINES
-- begin ICVRETAIL_WAREHOUSE_BALANCE
create table ICVRETAIL_WAREHOUSE_BALANCE (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY nvarchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY nvarchar(50),
    DELETE_TS datetime2,
    DELETED_BY nvarchar(50),
    --
    RECORDER uniqueidentifier,
    PERIOD datetime2,
    RECORD_TYPE nvarchar(50),
    NUM_LINE integer,
    WAREHOUSE_ID uniqueidentifier,
    PRODUCT_ID uniqueidentifier,
    QUANTITY decimal(19, 2),
    AMOUNT decimal(19, 2),
    NOTE nvarchar(max),
    --
    primary key nonclustered (ID)
)^
-- end ICVRETAIL_WAREHOUSE_BALANCE
