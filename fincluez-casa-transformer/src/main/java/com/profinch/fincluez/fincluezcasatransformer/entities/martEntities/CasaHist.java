package com.profinch.fincluez.fincluezcasatransformer.entities.martEntities;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@IdClass(CasaHistCK.class)
public class CasaHist implements Serializable {
    @Id
    private String accountBrnKey;
    @Id
    private String entityCode;
    @Id
    private Date dateHist;

    private String accountBlocked;
    private String accountDormant;
    private String accountFrozen;
    private String periodEnd;
    private String period;
    private String typeOfAccount;
    private String ratingAtOrigination;
    private String remainingTenorBucketKey;
    private String tenorBucketKey;
    private String tgtActypeBrnKey;
    private String tgtActypeRMKey;
    private String tgtODBrnKey;
    private String tgtODBrnProdKey;
    private String tgtODRMKey;
    private String tgtProdBrnKey;
    private String tgtProdRMKey;
    private String tgtProdRegionKey;
    private String overdueBucketKey;
    private String autoDepositApplicable;
    private String odLimitLine;
    private String year;
    private String accountHasTovToday;
    private String CrGL;
    private String DrGL;
    private String atmFacility;

    private Date dateClosed;
    private Date dateFirstDebitBookBal;
    private Date dateFirstOverDrawn;
    private Date dateLastPayment;
    private Date dateODLinkagesEnd;
    private Date dateODLinkagesStart;
    private Date dateOfLastCrActivity;
    private Date dateOfLastDrActivity;
    private Date dateTDLastRedem;
    private Date dateTDLastRollover;
    private Date dateTDMaturity;
    private Date dateTDOpen;
    private Date dateTDTopup;
    private Date dateTodLimitEnd;
    private Date dateTodLimitStart;
    private Date dateTodSince;
    private Date dateTotalODEnd;
    private Date dateTotalODStart;

    private int tenorRemainingTD;
    private int tenorTD;
    private int atmDailyTransactionCount;

    private double overdueDays;
    private double exchRate;
    private double amtAtmDailyLimitAcy;
    private double amtAtmDailyLimitLcy;
    private double amtAvailableBalanceAcy;
    private double amtAvailableBalanceLcy;
    private double amtAvlTodLimitAcy;
    private double amtAvlTodLimitLcy;
    private double amtBalOnAccountOpenAcy;
    private double amtBalOnAccountOpenLcy;
    private double amtBlockedAcy;
    private double amtBlockedLcy;
    private double amtCrAccrualAcy;
    private double amtCrAccrualLcy;
    private double amtCreditBalAcy;
    private double amtCreditBalLcy;
    private double amtCurrentBalanceAcy;
    private double amtCurrentBalanceLcy;
    private double amtDayLightLimitAcy;
    private double amtDayLightLimitLcy;
    private double amtDebitBalAcy;
    private double amtDebitBalLcy;
    private double amtDepositOutstandingAcy;
    private double amtDepositOutstandingLcy;
    private double amtDrAccrualAcy;
    private double amtDrAccrualLcy;
    private double amtExposureAtDefault;
    private double amtExposureAtDefaultLcy;
    private double amtInterestIncomeUnpaidAcy;
    private double amtInterestIncomeUnpaidLcy;
    private double amtLastPayment;
    private double amtLastPaymentLcy;
    private double amtMinBalAcy;
    private double amtMinBalLcy;
    private double amtMtdCrTovAcy;
    private double amtMtdCrTovLcy;
    private double amtMtdDrTovAcy;
    private double amtMtdDrTovLcy;
    private double amtODLinkagesAcy;
    private double amtODLinkagesAvlAcy;
    private double amtODLinkagesAvlLcy;
    private double amtODLinkagesLcy;
    private double amtSecurity;
    private double amtSecurityLcy;
    private double amtSubLimitAcy;
    private double amtSubLimitLcy;
    private double amtTDAcy;
    private double amtTDLcy;
    private double amtTDRedeemedAcy;
    private double amtTDRedeemedLcy;
    private double amtTDRolloverAcy;
    private double amtTDRolloverLcy;
    private double amtTDTopupAcy;
    private double amtTDTopupLcy;
    private double amtTDUnclaimedAcy;
    private double amtTDUnclaimedLcy;
    private double amtTodLimitAcy;
    private double amtTodLimitLcy;
    private double amtTodayChargesLiquidatedAcy;
    private double amtTodayInterestExpenseAcy;
    private double amtTodayInterestIncomeAcy;
    private double amtTodaysCrTovAcy;
    private double amtTodaysCrTovLcy;
    private double amtTodaysDrTovAcy;
    private double amtTodaysDrTovLcy;
    private double amtTotalODAvlAcy;
    private double amtTotalODAvlLcy;
    private double amtTotalODLimitAcy;
    private double amtTotalODLimitLcy;
    private double amtTotalODUtilizedAcy;
    private double amtTotalODUtilizedLcy;
    private double amtUncollectedAcy;
    private double amtUncollectedLcy;
    private double amtYtdChargesLiquidatedAcy;
    private double amtYtdChargesLiquidatedLcy;
    private double amtYtdCrTovAcy;
    private double amtYtdCrTovLcy;
    private double amtYtdDrTovAcy;
    private double amtYtdDrTovLcy;
    private double amtYtdInterestExpenseAcy;
    private double amtYtdInterestExpenseLcy;
    private double amtYtdInterestIncomeAcy;
    private double amtYtdInterestIncomeLcy;
    private double amtYtdInterestLiquidatedAcy;
    private double amtYtdInterestLiquidatedLcy;
    private double monthlyAvgAvlBalAcy;
    private double monthlyAvgAvlBalLcy;
    private double mtdMaxBalAcy;
    private double mtdMaxBalLcy;
    private double mtdMinBalAcy;
    private double mtdMinBalLcy;
    private double ytdAvgBalAcy;
    private double ytdAvgBalLcy;
    private double openingBalAnceAcy;
    private double openingBalanceLcy;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
