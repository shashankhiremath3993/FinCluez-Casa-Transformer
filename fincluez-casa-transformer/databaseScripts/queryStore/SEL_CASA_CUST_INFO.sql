insert into fincluez_infra.query_store (id_query,query_description,enabled,query_string) values
('SEL_CASA_CUST_INFO','Mart Query 1',true,
 'SELECT COALESCE(B.CUSTOMER_NO, A.CUSTOMER_NO)       as customerNo,
         COALESCE(B.CUSTOMER_NAME1, A.CUSTOMER_NAME1)    as customerName1,
         COALESCE(B.SHORT_NAME, A.SHORT_NAME) as shortName,
         COALESCE(B.COUNTRY, A.COUNTRY) as country,
         CUSTOMER_CATEGORY as customerCategory,
         COALESCE(B.CUSTOMER_TYPE, A.CUSTOMER_TYPE) as customerType,
         LIABILITY_NO as liabilityNo
    FROM STG_STTM_CUSTOMER A
    LEFT JOIN STG_STTM_CORE_CUSTOMER B
      ON (A.CUSTOMER_NO = B.CUSTOMER_NO
          AND A.ENTITY_CODE = B.ENTITY_CODE
          AND A.BRANCH_CODE = B.BRANCH_CODE
          AND A.el_run_date = B.el_run_date )
   WHERE EXISTS (SELECT 1
           FROM STG_STTM_CUST_ACCOUNT B
           WHERE A.CUSTOMER_NO = B.CUST_NO
           AND A.ENTITY_CODE = B.ENTITY_CODE
           AND A.BRANCH_CODE = B.BRANCH_CODE
           AND A.el_run_date = B.el_run_date
           )
           AND A.ENTITY_CODE = :entityCode
           AND A.BRANCH_CODE = :branchcode
           AND A.CUST_AC_NO = :customeraccountnumber
           AND A.el_run_date = :elRunDate');

