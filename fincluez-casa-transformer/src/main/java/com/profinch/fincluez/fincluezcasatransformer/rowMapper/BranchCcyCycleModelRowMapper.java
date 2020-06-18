package com.profinch.fincluez.fincluezcasatransformer.rowMapper;

import com.profinch.fincluez.fincluezcasatransformer.models.BranchCcyCycleModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchCcyCycleModelRowMapper implements RowMapper<BranchCcyCycleModel> {

    @Override
    public BranchCcyCycleModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        BranchCcyCycleModel branchCcyCycleModel = new BranchCcyCycleModel();
        branchCcyCycleModel.setBranchCode(rs.getString("branchCode"));
        branchCcyCycleModel.setBranchLcy(rs.getString("branchLcy"));
        branchCcyCycleModel.setCurrentCycle(rs.getString("currentCycle"));
        branchCcyCycleModel.setCurrentPeriod(rs.getString("currentPeriod"));
        branchCcyCycleModel.setPcStartDate(rs.getDate("pcStartDate"));
        branchCcyCycleModel.setPcEndDate(rs.getDate("pcEndDate"));
        branchCcyCycleModel.setFcStartDate(rs.getDate("fcStartDate"));
        branchCcyCycleModel.setFcEndDate(rs.getDate("fcEndDate"));
        branchCcyCycleModel.setPrevFCStartDate(rs.getDate("prevFCStartDate"));
        branchCcyCycleModel.setPrevFCEndDate(rs.getDate("prevFCEndDate"));
        branchCcyCycleModel.setQuarterStartdate(rs.getDate("quarterStartdate"));
        branchCcyCycleModel.setQuarterLastDate(rs.getDate("quarterLastDate"));
        branchCcyCycleModel.setToday(rs.getDate("today"));
        branchCcyCycleModel.setNextWorkingDay(rs.getDate("nextWorkingDay"));
        branchCcyCycleModel.setMonthStartDate(rs.getDate("monthStartDate"));
        branchCcyCycleModel.setMonthEndDate(rs.getDate("monthEndDate"));
        branchCcyCycleModel.setPrevWorkingDay(rs.getDate("prevWorkingDay"));
        return branchCcyCycleModel;

    }
}

