package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


@Getter
@Setter
public class BranchCcyCycleModel {

    private String branchCode;
    private String branchLcy;
    private String currentCycle;
    private String currentPeriod;
    private Date pcStartDate;
    private Date pcEndDate;
    private Date fcStartDate;
    private Date fcEndDate;
    private Date prevFCStartDate;
    private Date prevFCEndDate;
    private Date quarterStartdate;
    private Date quarterLastDate;
    private Date today;
    private Date nextWorkingDay;
    private Date monthStartDate;
    private Date monthEndDate;
    private Date prevWorkingDay;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
