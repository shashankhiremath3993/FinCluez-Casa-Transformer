insert into query_store (id_query,query_description,enabled,query_string) values
('SEL_CASA_DIM_ACCOUNT','Query to load DimAccountModel',true,
'SELECT A.Account       as account,
        A.Account_Brn_Key as acccountBrnKey,
        B.Branch        as branch
   FROM Dim_Account A, Dim_Branch B
  WHERE A.Branch_Key = B.Branch_Key
  AND A.Entity_Code = B.Entity_Code
  and A.Entity_Code = :entityCode
 and  A.Account = :account
 and  B.Branch  = :branch');