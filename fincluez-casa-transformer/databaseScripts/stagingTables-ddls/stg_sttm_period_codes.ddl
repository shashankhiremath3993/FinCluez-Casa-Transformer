CREATE TABLE STG_STTM_PERIOD_CODES (PERIOD_CODE VARCHAR(3) NOT NULL , FIN_CYCLE VARCHAR(9) NOT NULL , PC_START_DATE DATE, PC_END_DATE DATE, ENTITY_CODE VARCHAR(35),  CONSTRAINT PK01_STG_STTM_PERIOD_CODES PRIMARY KEY (ENTITY_CODE, PERIOD_CODE, FIN_CYCLE)   )