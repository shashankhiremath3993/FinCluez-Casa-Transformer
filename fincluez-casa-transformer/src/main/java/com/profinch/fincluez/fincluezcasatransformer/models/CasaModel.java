package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;


@Getter
@Setter
public class CasaModel {

    private String customerNo;
    private String custAcNo;
    private String branchCode;
    private String prevStatus;
    private String currStatus;
    private String acc;
    private String brn;
    private String acNo;
    private String account;
    private String branch;
    private String linkageType;
    private String linkedRefNo;
    //linkagePercentage
    private String linkageBranch;
    private String linkedCcy;
    private int liabNo;
    private String ccy1;
    private String ccy2;
    private String rateType;
    private String accountClass;
    private String ccyCode;
    private String sex;
    private String customerName1;
    private String shortName;
    private String country;
    private String customerCategory;
    private String customerType;
    private int liabilityNo;
    private String currentCycle;
    private String brnachLcy;
    private String currentPeriod;
    private String limitCcy;
    private String accountStatus;

    //double
    private double monthlyAggrBalLcy;
    private double lcyClosingBal;
    private double acyClosingBal;
    private double chequeRejectionCount;
    private double acyAccruedCrIc;
    private double tdAmount;
    private double amtYtdDrTovAcy;
    private double amtYtdDrTovLcy;
    private double amtYtdCrTovAcy;
    private double amtYtdCrTovLcy;
    private double amtYtdIntCurrExpenseAcy;
    private double amtTodayIntCurrExpenseAcy;
    private double amtYtdIntCurrIncomeAcy;
    private double amtTodayIntCurrIncomeAcy;
    private double amtYtdChargesLiquidateAcy;
    private double amtTodayChargeLiquidateAcy;
    private double amtYtdInterestLiquidateedAcy;
    private double amtYtdIntHistExpenseAcy;
    private double amtTodayIntHistExpenseAcy;
    private double amtYtdIntHistIncomeAcy;
    private double amtTodayIntHistIncomeAcy;
    private double amtLastPayment;
    private double limitAmount;
    private double utilization;
    private double availableAmount;
    private double midRate;
    //buySpread;
    //saleSpread;
    private double buyRate;
    private double saleRate;
    private double powerFactor;
    private double minBalance;
    private double minOpeningBal;
    private double totalRedeemAmt;
    private double topUpAmt;
    private double tdRollAmt;
    private double tdRedeemAmt;
    private double uncliamedInterest;

    //Date
    private Date maturityDate;
    private Date DateLastPayment;
    private Date minBkgDate;
    private Date acOpendate;
    private Date limitStartDate;
    private Date limitEndDate;
    private Date dateOfBirth;
    private Date pcStartDate;
    private Date pcEndDate;
    private Date fcStartDate;
    private Date fcEndDate;
    private Date prevFcStartDate;
    private Date prevFcEndDate;
    private Date quarterLatDate;
    private Date monthStartDate;
    private Date monthEndDate;
    private Date prevWorkingDay;
    private Date nextWorkingDay;
    private Date renewalDate;
    private Date topUpValDate;
    private Date tdRollDate;
    private Date tdRedeemDate;
    private Date valDate;
    private Date today;
    private Date closureDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
