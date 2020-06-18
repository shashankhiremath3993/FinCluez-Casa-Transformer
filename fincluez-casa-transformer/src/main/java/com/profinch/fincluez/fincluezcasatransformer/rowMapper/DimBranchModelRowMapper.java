package com.profinch.fincluez.fincluezcasatransformer.rowMapper;


import com.profinch.fincluez.fincluezcasatransformer.models.DimBranchModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DimBranchModelRowMapper implements RowMapper<DimBranchModel> {
    @Override
    public DimBranchModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        DimBranchModel dimBranchModel = new DimBranchModel();
        //dimBranchModel.setBranchRegionKey(rs.getString("branchRegionKey"));
        dimBranchModel.setBranchRegion(rs.getString("branchRegion"));
        //dimBranchModel.setColorBranchRegion(rs.getString("colorBranchRegion"));
        dimBranchModel.setBranchKey(rs.getString("branchKey"));
        dimBranchModel.setBranch(rs.getString("branch"));
        dimBranchModel.setBranchName(rs.getString("branchName"));
        dimBranchModel.setBranchDesc(rs.getString("branchDesc"));
        dimBranchModel.setColorBranch(rs.getString("colorBranch"));
        dimBranchModel.setBranchBusinessKey(rs.getString("branchBusinessKey"));
        dimBranchModel.setDateCurrFinYearStart(rs.getDate("dateCurrFinYearStart"));
        dimBranchModel.setDateCurrFinYearEnd(rs.getDate("dateCurrFinYearEnd"));
        dimBranchModel.setDatePrevFinYearStart(rs.getDate("datePrevFinYearStart"));
        dimBranchModel.setDatePrevFinYearEnd(rs.getDate("datePrevFinYearEnd"));
        dimBranchModel.setPrevYear(rs.getString("prevYear"));
        dimBranchModel.setPrevPeriod(rs.getString("prevPeriod"));
        dimBranchModel.setDateCurrQuarterStart(rs.getDate("dateCurrQuarterStart"));
        dimBranchModel.setDateCurrQuarterEnd(rs.getDate("dateCurrQuarterEnd"));
        dimBranchModel.setDateCurrPeriodStart(rs.getDate("dateCurrPeriodStart"));
        dimBranchModel.setDateCurrPeriodEnd(rs.getDate("dateCurrPeriodEnd"));
        dimBranchModel.setApplicationDate(rs.getDate("applicationDate"));
        dimBranchModel.setDatePrevWorking(rs.getDate("datePrevWorking"));
        dimBranchModel.setDateNextWorking(rs.getDate("dateNextWorking"));
        //dimBranchModel.setBranchRecordStat(rs.getString("branchRecordStat"));
        dimBranchModel.setCurrYear(rs.getString("currYear"));
        dimBranchModel.setCurrPeriod(rs.getString("currPeriod"));
        //dimBranchModel.setWalkinCustomer(rs.getString("walkinCustomer"));
        //dimBranchModel.setParentBranch(rs.getString("parentBranch"));
        //dimBranchModel.setRegionalOffice(rs.getString("regionalOffice"));
        dimBranchModel.setBranchLcy(rs.getString("branchLcy"));

        return dimBranchModel;
    }
}
