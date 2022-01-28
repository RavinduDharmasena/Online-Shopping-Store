CREATE TABLE SHOP_PRODUCT_CATEGORIES(
    CATEGORY_ID             NUMBER(4) IDENTITY,
    CATEGORY_NAME           VARCHAR2(100),
    CATEGORY_DESCRIPTION    VARCHAR2(500),
    PARENT_CATEGORY_ID      NUMBER(4),
    CATEGORY_STATUS         NUMBER(1),
    
    CONSTRAINT PRODUCT_CATEGORIES_PK PRIMARY KEY(CATEGORY_ID)
);

--CREATE TABLE SHOP_PRODUCTS(
--    PRODUCT_ID NUMBER(4) GENERATED BY DEFAULT ON NULL AS IDENTITY,
--    PRODUCT_NAME VARCHAR2(100),
--    PRODUCT_DESCRIPTION VARCHAR2(500),
--    PRODUCT_CATEGORY_ID NUMBER(4),
--    IS_ACTIVE NUMBER(1),
--    IS_DELETED NUMBER(1),
--    
--    CONSTRAINT CHK_PRODUCTS_PK PRIMARY KEY(PRODUCT_ID),
--    CONSTRAINT CHK_PRODUCT_FK1 FOREIGN KEY(PRODUCT_CATEGORY_ID) REFERENCES SHOP_PRODUCT_CATEGORIES(CATEGORY_ID)
--);