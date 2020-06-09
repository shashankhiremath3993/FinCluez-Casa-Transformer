package com.profinch.fincluez.fincluezcasatransformer.models;

import com.profinch.fincluez.finclueztlibrary.transformer.TransformerInputModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

@Getter
@Setter
public class CasaTransformationInputModel extends TransformerInputModel {

    private String custAcNo;
    private String acDesc;
    private String custNo;
    private String ccy;
    private String accountClass;
    private String acStatNoDr;
    private String acStatNoCr;
    private String acStatBlock;
    private String acStatStopPay;
    private String acStatDormant;
    private String jointAcIndicator;
    private String acStmtCycle;
    private String altAcNo;
    private String chequeBookFacility;
    private String atmFacility;
    private String passbookFacility;
    private String acStmtType;
    private String drHoLine;
    private String crHoLine;
    private String crCbLine;
    private String drCbline;
    private String acStatFrozen;
    private String drGl;
    private String crGl;
    private String recordStat;
    private String authStat;
    private String makerId;
    private String checkerId;
    private String onceAuth;
    private String limitCcy;
    private String lineId;
    private String casAccount;
    private String hasTover;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String typeOfChq;
    private String amtCustAcNo;
    private String GenStmtOnlyOnMvmt;
    private String AcStatDePost;
    private String displayIbanInAdvices;
    private String clearingBankCode;
    private String clearingAcNo;
    private String IbanAcNo;
    private String accStmtType2;
    private String acStmtCycle2;
    private String genStmtOnlyOnMvmt2;
    private String accStmtType3;
    private String acStmtCycle3;
    private String genStmtOnlyOnMvmt3;
    private String masterAccountNo;
    private String casCustomer;
    private String accountType;
    private String trackReceivable;
    private String chequeBookName1;
    private String chequeBookName2;
    private String autoReorderChequeRequired;
    private String accStatus;
    private String statusChangeAutomatic;
    private String dormantParam;
    private String exposureCategory;
    private String StatementAccount;
    private String accountDerivedStatus;
    private String provCcyType;
    private String funding;
    private String fundinBranch;
    private String fundingAccount;
    private String location;
    private String media;
    private String modeOfOperation;
    private String trnOverLimitCode;
    private String countryCode;
    private String consolChargeAcc;
    private String linkedDepBranch;
    private String linkedDepAcc;
    private String accountAutoClosed;
    private String acSetClose;
    private String sweepRequired;
    private String sweepOut;
    private String autoCreatePool;
    private String defaultWaiver;
    private String autoDcRequest;
    private String autoChequeRequest;
    private String intermediaryRequired;
    private String fundId;
    private String salaryAccount;
    private String branchCode;
    private String entityCode;//20-Aug-2018

    //Date variables
    private Date acOpenDate;
    private Date previousStatment;
    private Date todLimitStartDate;
    private Date todLimitEndDate;
    private Date makerDateStamp;
    private Date checkerDateStamp;
    private Date dateLastCrActivity;
    private Date dateLastDrActivity;
    private Date dateLastDr;
    private Date dateLastCr;
    private Date todStartDate;
    private Date todEndDate;
    private Date dormancyDate;
    private Date lastCcyConvDate;
    private Date previousStatementDate2;
    private Date previousStatementDate3;
    private Date statusSince;
    private Date overdraftSince;
    private Date prevOvdDate;
    private Date overLineOdSince;
    private Date todSince;
    private Date prevTodSince;
    private Date crLmStartDate;
    private Date crLmRevDate;
    private Date noOfChequeRejResetOn;
    private Date acSetCloseDate;
    private Date lastPurgeDate;

    //Integer variables

    private int atmDailyCountLimit;

    //double variables

    private double acStatDay;
    private double subLimit;
    private double uncollFundsLimit;
    private double perviousStatmentBalance;
    private double perviousStatmentNo;
    private double todLimit;
    private double modNo;
    private double offlineLimit;
    private double acyOpeningBal;
    private double lcyOpeningBal;
    private double acyTodayToverDr;
    private double lcyTodayToverDr;
    private double acyTodayToverCr;
    private double lcyTodayToverCr;
    private double acyTankCr;
    private double acyTankDr;
    private double lcyTankCr;
    private double lcyTankDr;
    private double acyToverCr;
    private double lcyToverCr;
    private double acyTankUncollected;
    private double acyCurrBalance;
    private double lcyCurrBalance;
    private double acyBlockedAmount;
    private double acyAvlBalance;
    private double acyUnauthDr;
    private double acyUnauthTankDr;
    private double acyUnauthCr;
    private double acyUnauthTankCr;
    private double acyUnauthUncollected;
    private double acyUnauthTankUncollected;
    private double acyMtdToverDr;
    private double lcyMtdToverDr;
    private double acyMtdToverCr;
    private double lcyMtdToverCr;
    private double acyAccruedDrIc;
    private double acyAccruedCrIc;
    private double acyUncollected;
    private double dormancyDays;
    private double atmDailyAmtLimit;
    private double acc_stmtDays2;
    private double previousStatementBalance2;
    private double previousStatementNo2;
    private double previousStatementBalance3;
    private double previousStatementNo3;
    private double sweepType;
    private double autoDepositeBal;
    private double minReqdBal;
    private double crAutoExRateLimit;
    private double drAutoExRateLimit;
    private double receivableAmount;
    private double autoReorderChequeLevel;
    private double autoReorderCheckLeaves;
    private double drIntDue;
    private double riskFreeExpAmount;
    private double provisionAmount;
    private double creditTxnLimit;
    private double chaegeDue;
    private double interimDebitAmt;
    private double interimCreditAmt;
    private double interimStmtDayCount;
    private double interimStmtYtdCount;
    private double dayLightLimitAmount;
    private double passbookNumber;
    private double maxNoChequeRej;
    private double noChequeRej;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
