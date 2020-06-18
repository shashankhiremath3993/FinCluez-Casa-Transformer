package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.sql.Date;

@Getter
@Setter
public class DimBranchModel {

    private String branchRegionKey;
    private String branchRegion;
    private String colorBranchRegion;
    private String branchKey;
    private String branch;
    private String branchName;
    private String branchDesc;
    private String colorBranch;
    private String branchBusinessKey;
    private Date dateCurrFinYearStart;
    private Date dateCurrFinYearEnd;
    private Date datePrevFinYearStart;
    private Date datePrevFinYearEnd;
    private String prevYear;
    private String prevPeriod;
    private Date dateCurrQuarterStart;
    private Date dateCurrQuarterEnd;
    private Date dateCurrPeriodStart;
    private Date dateCurrPeriodEnd;
    private Date applicationDate;
    private Date datePrevWorking;
    private Date dateNextWorking;
    private String branchRecordStat;
    private String currYear;
    private String currPeriod;
    private String walkinCustomer;
    private String parentBranch;
    private String regionalOffice;
    private String branchLcy;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
