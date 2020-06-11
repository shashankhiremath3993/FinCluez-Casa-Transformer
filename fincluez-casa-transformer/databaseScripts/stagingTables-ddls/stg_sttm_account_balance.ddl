CREATE TABLE fincluez_staging.STG_STTM_ACCOUNT_BALANCE (CUST_AC_BRN VARCHAR(3) NOT NULL, CUST_AC_NO VARCHAR(20) NOT NULL, ACY_WITHDRAWABLE_BAL DECIMAL(22,3), ACY_UNCOLLECTED DECIMAL(22,3), ACY_BLOCKED_AMT DECIMAL(22,3), WITHDRAWABLE_UNCOLLED_FUND DECIMAL(22,7), AC_STAT_DORMANT VARCHAR(1), RECEIVABLE_AMOUNT DECIMAL, ACY_ECA_BLOCKED_AMT DECIMAL DEFAULT 0, CCY VARCHAR(3), ACY_UNAUTH_CR DECIMAL DEFAULT 0, ENTITY_CODE VARCHAR(35), EL_RUN_DATE DATE, CONSTRAINT PK01_STG_STTM_ACCOUNT_BALANCE PRIMARY KEY (CUST_AC_NO, CUST_AC_BRN, EL_RUN_DATE))