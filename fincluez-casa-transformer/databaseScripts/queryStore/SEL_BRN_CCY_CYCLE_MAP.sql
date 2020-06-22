insert into query_store (id_query,query_description,enabled,query_string) values
('SEL_BRN_CCY_CYCLE_MAP','Query to load BranchCcyCycleModel',true,'SELECT COALESCE(AA.BRANCH_CODE,A.BRANCH_CODE) as branchCode,
       COALESCE(AA.BRANCH_LCY,A.BRANCH_LCY) as branchLcy,
       CURRENT_CYCLE as currentCycle,
       CURRENT_PERIOD as currentPeriod,
       P.PC_START_DATE as pcStartDate,
       P.PC_END_DATE as pcEndDate,
       X.FC_START_DATE as fcStartDate,
       X.FC_END_DATE as fcEndDate,
       ADD_MONTHS(X.FC_START_DATE, -12) as prevFCStartDate,
       ADD_MONTHS(X.FC_END_DATE, -12) as prevFCEndDate,
       TRUNC(Y.TODAY, 'Q') as quarterStartdate,
       TRUNC(ADD_MONTHS(Y.TODAY, +3), 'Q') - 1 as quarterLastDate,
       Y.today as today,
       TRUNC(Y.TODAY, 'month') as monthStartDate,
       LAST_DAY(Y.TODAY) as monthEndDate,
       Y.PREV_WORKING_DAY as preWorkingDay,
       Y.NEXT_WORKING_DAY as nextWorkingDay
  FROM STG_STTM_BRANCH A LEFT JOIN STG_STTM_CORE_BRANCH AA ON (A.BRANCH_CODE = AA.BRANCH_CODE),
       (SELECT FIN_CYCLE,
               PERIOD_CODE,
               PC_START_DATE,
               PC_END_DATE,
               ENTITY_CODE
          FROM STG_STTM_PERIOD_CODES
         WHERE ENTITY_CODE =  :entityCode
		       ) P,
       (SELECT FIN_CYCLE, FC_START_DATE, FC_END_DATE, ENTITY_CODE
          FROM STG_STTM_FIN_CYCLE
         WHERE ENTITY_CODE =  :entityCode) X,
       (SELECT NEXT_WORKING_DAY,
               BRANCH_CODE,
               TODAY,
               PREV_WORKING_DAY,
               ENTITY_CODE
          FROM STG_STTM_DATES
         WHERE ENTITY_CODE =  :entityCode) Y
 WHERE X.FIN_CYCLE = A.CURRENT_CYCLE
   AND A.ENTITY_CODE = Y.ENTITY_CODE
   AND A.BRANCH_CODE = Y.BRANCH_CODE
   AND A.ENTITY_CODE = P.ENTITY_CODE
   AND A.ENTITY_CODE = X.ENTITY_CODE
   AND P.FIN_CYCLE = A.CURRENT_CYCLE
   AND P.PERIOD_CODE = A.CURRENT_PERIOD
   AND A.ENTITY_CODE = :entityCode
   AND A.BRANCH_CODE = :branchCode  
   AND A.ELRUN_DATE = :elRunDate');