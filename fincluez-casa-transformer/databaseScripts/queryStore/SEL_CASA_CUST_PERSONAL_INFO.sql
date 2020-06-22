insert into query_store (id_query,query_description,enabled,query_string) values
('SEL_CASA_CUST_PERSONAL_INFO','Mart Query 2',true,'SELECT A.CUSTOMER_NO as customerNo,
       A.DATE_OF_BIRTH as dateOfBirth,
       A.SEX as sex
  FROM STG_STTM_CUST_PERSONAL A
 WHERE EXISTS (SELECT 1
          FROM STG_STTM_CUST_ACCOUNT B
         WHERE B.CUST_NO = A.CUSTOMER_NO
            AND B.ENTITY_CODE = A.ENTITY_CODE
            )AND A.ENTITY_CODE = :entityCode
            and A.CUSTOMER_NO = :customerAccountNumber');