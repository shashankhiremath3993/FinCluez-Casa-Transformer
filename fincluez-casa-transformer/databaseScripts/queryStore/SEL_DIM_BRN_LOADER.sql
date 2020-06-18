insert into query_store (id_query,query_description,enabled,query_string) values
('SEL_DIM_BRN_LOADER','Query to load DimBranchModel',true,'select Branch_Key            as branchKey,
       Branch               as branch,
       Branch_Name           as branchName,
       Branch_Desc           as branchDesc,
       Color_Branch          as colorBranch,
       Branch_Business_Key    as branchBusinessKey,
       Date_Curr_Fin_Year_Start as dateCurrFinYearStart,
       Date_Curr_Fin_Year_End   as dateCurrFinYearEnd,
       Date_Prev_Fin_Year_Start as datePrevFinYearStart,
       Date_Prev_Fin_Year_End   as datePrevFinYearEnd,
       Prev_Year             as prevYear,
       Prev_Period           as prevPeriod,
       Date_Curr_Quarter_Start as dateCurrQuarterStart,
       Date_Curr_Quarter_End   as dateCurrQuarterEnd,
       Date_Curr_Period_Start  as dateCurrPeriodStart,
       Date_Curr_Period_End    as dateCurrPeriodEnd,
       Application_Date      as applicationDate,
       Date_Prev_Working      as datePrevWorking,
       Date_Next_Working      as dateNextWorking,
       Curr_Year             as currYear,
       Curr_Period           as currPeriod,
       Branch_Lcy            as branchLcy,
       r.Branch_Region       as branchRegion
  from Dim_Branch b, Dim_Branch_Region r
 where b.Branch_Region_Key = r.Branch_Region_Key
    and b.Entity_Code = r.Entity_Code
    and b.Entity_Code = :entityCode
	and b.Branch = :branchCode')