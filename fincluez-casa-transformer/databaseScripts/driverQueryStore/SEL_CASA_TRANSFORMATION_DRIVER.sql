insert into fincluez_infra.driver_query_store(
id_query,
  query_description,
   select_query_string,
   from_query_string,
   where_query_string,
   sort_query_string
   )
values ('SEL_CASA_TRANSFORMATION_DRIVER',
'Query to populate the casa transformation queue',
'select branch_code,cust_ac_no,el_run_date,entity_code',
 'from stg_sttm_cust_account',
'where entity_code = :entityCode and branch_code = :branchCode and el_run_date = :elRunDate',
'cust_ac_no')