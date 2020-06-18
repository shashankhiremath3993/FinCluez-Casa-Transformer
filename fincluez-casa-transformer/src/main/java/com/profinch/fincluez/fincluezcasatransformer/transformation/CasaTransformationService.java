package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.Casa;
import com.profinch.fincluez.fincluezcasatransformer.models.*;
import com.profinch.fincluez.finclueztlibrary.constants.ProcessStatus;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.DimAccount;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.DimBranch;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.DimBranchCK;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.service.DimCacheService;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerInterface;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerOutputModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CasaTransformationService implements TransformerInterface {

    private Logger log = LoggerFactory.getLogger(CasaTransformationService.class);

    @Autowired
    private SelectCasaData selectCasaData;
    @Autowired
    private  SelectDimBrnLoader selectDimBrnLoader;
    @Autowired
    private SelectCasaDimAccount selectCasaDimAccount;


    @Override
    public TransformerOutputModel performTransformation(TransformationQueue transformationQueue) {

        CasaTransformationInputModel ctiModelFrom_SEL_CASA_DATA = selectCasaData.selectCasaData(transformationQueue);
        log.debug("CTI model from SEL_CASA_DATA query {}",ctiModelFrom_SEL_CASA_DATA.toString());

        double  l_ytdAvgrBalAcy = 0.00, l_ytdAvgrBalLcy = 0.00, l_mtdMinBalAcy = 0.00, l_mtdMinBalLcy = 0.00,
                l_mtdMaxBalAcy = 0.00, l_mtdMaxBalLcy = 0.00, l_amtATMDailyLimitAcy = 0.00,
                l_amtAvailableBalanceAcy = 0.00, l_amtCreditBalAcy = 0.00, l_amtDebitBalAcy = 0.00,
                l_amtBlockedAcy = 0.00, l_amtCrAccrualAcy = 0.00, l_amtDrAccrualAcy = 0.00,
                l_amtCurrentBalanceAcy = 0.00, l_amtCurrentBalanceLcy = 0.00, l_amtDaylightLimitAcy = 0.00,
                l_amtTodaysDrTovAcy = 0.00, l_amtTodaysDrTovLcy = 0.00, l_amtTodaysCrTovAcy = 0.00,
                l_amtTodaysCrTovLcy = 0.00, l_amtMtdCrTovAcy = 0.00, l_amtMtdCrTovLcy = 0.00, l_amtMtdDrTovAcy = 0.00,
                l_amtMtdDrTovLcy = 0.00, l_openingBalanceAcy = 0.00, l_openingBalanceLcy = 0.00, l_amtTodLimitAcy = 0.00,
                l_amtSubLimitAcy = 0.00, l_amtUncollectedAcy = 0.00, l_amtMinBalAcy = 0.00, l_exchRate = 0.00,l_exchRateCol = 0.00,
                l_amtMinBalLcy = 0.00, l_amtATMDailyLimitLcy = 0.00,
                l_amtAvailableBalanceLcy = 0.00, l_amtCreditBalLcy = 0.00, l_amtDebitBalLcy = 0.00,
                l_amtBlockedLcy = 0.00, l_amtCrAccrualLcy = 0.00, l_amtDrAccrualLcy = 0.00,
                l_amtDaylightLimitLcy = 0.00, l_amtTODLimitLcy = 0.00, l_amtSubLimitLcy = 0.00,
                l_amtUncollectedLcy = 0.00, l_amtAvlTodLimitAcy = 0.00, l_amtAvlTodLimitLcy = 0.00,
                l_amtTotalODUtilizedAcy = 0.00, l_amtTotalODUtilizedLcy = 0.00, l_limitExchrate = 0.00,
                l_limitAmt = 0.00, l_limitAmtAcy = 0.00, l_limitAvailAmt = 0.00, l_limitAvailAmtAcy = 0.00,
                l_amtTotalODLimitAcy = 0.00, l_amtTotalODLimitLcy = 0.00, l_amtODLimitAcy = 0.00,
                l_amtTotalODAvlAcy = 0.00, l_amtTotalODAvlLcy = 0.00, l_amtODLinkagesAcy = 0.00,
                l_amtODLinkagesLcy = 0.00, l_amtODLinkagesAvlAcy = 0.00, l_amtODLinkagesAvlLcy = 0.00,
                l_amtYtdChargesLiquidatedAcy = 0.00, l_amtYtdInterestLiquidatedAcy = 0.00,
                l_amtYtdInterestExpenseAcy = 0.00, l_amtYtdHistInterestExpAcy = 0.00, l_amtYtdHistInterestIncAcy = 0.00,
                l_amtYtdInterestIncomeAcy = 0.00, l_amtTodayInterestIncomeAcy = 0.00,
                l_amtTodayHistInterestIncAcy = 0.00, l_amtInterestIncomeUnPaidAcyToday = 0.00,
                l_amtTodayInterestExpenseAcy = 0.00, l_amtTodayHistInterestExpAcy = 0.00,
                l_amtTodayCurrInterestExpAcy = 0.00, l_amtTodayChargesLiquidatedAcy = 0.00,
                l_amtYtdCurrInterestExpAcy = 0.00, l_amtYtdChargesLiquidatedLcy = 0.00,
                l_amtYtdInterestLiquidatedLcy = 0.00, l_amtYtdInterestExpenseLcy = 0.00,
                l_amtYtdInterestIncomeLcy = 0.00, l_amtYTDDrTovAcy = 0.00, l_amtYTDDrTovLcy = 0.00,
                l_amtYTDCrTovAcy = 0.00, l_amtYTDCrTovLcy = 0.00, l_monthlyAvgAvlBalACy = 0.00,
                l_monthlyAvgAvlBalLCy = 0.00, l_amtTermDepositAcy = 0.00, l_amtTermDepositLcy = 0.00,
                l_amtBalOnAccountOpenAcy = 0.00, l_amtBalOnAccountOpenLcy = 0.00, l_amttdunclaimedAcy = 0.00,
                l_amtTDUnclaimedLcy = 0.00, l_amtDepositOutstandingAcy = 0.00, l_amtDepositOutstandingLcy = 0.00,
                l_amtTdredeemedAcy = 0.00, l_amtTdredeemedLcy = 0.00, l_amtInterestIncomeUnPaidAcy = 0.00,
                l_amtInterestIncomeUnPaidLcy = 0.00, l_amtTdRolloverAcy = 0.00, l_amtTdRolloverLcy = 0.00,
                l_amtTdTopUPAcy = 0.00, l_amtTdTopUPLcy = 0.00,
                l_amtLastPayment = 0.00, l_amtLastPaymentLcy = 0.00, l_amtSecurity = 0.00, l_amtSecurityLcy = 0.00,
                l_amtExposureAtDefault = 0.00, l_amtExposureAtDefaultLcy = 0.00,
                // Vitthal 14-11-2017 starts

                l_amtAvailableBalanceToday = 0.0, l_amtAvailableBalanceCurrent = 0.0,
                l_amtAvailableBalanceMonthEnd = 0.0, l_amtCrAccrualToday = 0.0, l_amtDrAccrualToday = 0.0,
                l_amtCurrentBalanceToday = 0.0, l_amtCurrentBalanceCurrent = 0.0, l_amtCurrentBalanceMonthEnd = 0.0,
                l_amtDrTovToday = 0.0, l_amtCrTovToday = 0.0, l_amtDrIntLiquidatedToday = 0.0,
                l_amtCrIntLiquidatedToday = 0.0, l_amtChargesLiquidatedToday = 0.0, l_amtAvailableBalanceTodayLcy = 0.0,
                l_amtAvailableBalanceCurrentLcy = 0.0, l_amtAvailableBalanceMonthEndLcy = 0.0,
                l_amtCrAccrualTodayLcy = 0.0, l_amtDrAccrualTodayLcy = 0.0, l_amtCurrentBalanceTodayLcy = 0.0,
                l_amtCurrentBalanceCurrentLcy = 0.0, l_amtCurrentBalanceMonthEndLcy = 0.0, l_amtDrTovTodayLcy = 0.0,
                l_amtCrTovTodayLcy = 0.0, l_amtDrIntLiquidatedTodayLcy = 0.0, l_amtCrIntLiquidatedTodayLcy = 0.0,
                l_amtChargesLiquidatedTodayLcy = 0.0
                        ;

        String  l_customerAccountId = null,l_branch = null,l_product = null,l_currency = null,
                l_accountBlocked = null,l_accountDormant = null,l_accountFrozen = null,l_accountHasTovToday = null,
                l_atmFacility = null, l_autoDepositApplicable = null, l_odLimitLine = null,
                l_accountType = null, l_brnLcy = null,
                l_key = null, l_limitCcy = null, l_linkedRefNo = null, l_limitLiabNo = null,
                l_tenorBuckettd = null, l_remainingTenorBuckettd = null, l_overDueBucket = null,
                l_currentRating = null, l_accountBrnKey = null, l_tenorBucketKey = null,
                l_remainingTenorBucketKey = null, l_overdueBucketKey = null, l_monthEnd = null,
                l_tgtActypeRMKey = null,l_tgtActypeBrnKey = null,l_tgtProdRMKey = null,l_tgtProdBrnKey = null,
                l_tgtProdRegionKey = null,l_tgtODRMKey = null,	l_tgtODBrnKey = null	,l_tgtODBrnProdKey = null,l_acType = "CASA",
                l_rm = null,l_region = null,l_overdueBucket = null,
                l_accPrevStatus = null,l_accCurrStatus = null,l_periodEnd = null,l_period = null, l_year= null, l_mStatus = null
                ;

        Date l_dateStart = null;
        Date l_dateOfLastDrActivity = null;
        Date l_dateOfLastCrActivity = null;
        Date l_dateTODLimitStart = null;
        Date l_dateTODLimitEnd = null;
        Date l_dateTODSince = null;
        Date l_applicationDate = null;
        Date l_dateCurrFinYearStart = null;
        Date l_dateCurrFinYearEnd = null;
        Date l_limitStartDate = null;
        Date l_limitEnddate = null;
        Date l_dateTotalODStart = null;
        Date l_dateTotalODEnd = null;
        Date l_monthStartdate = null;
        Date l_monthEnddate = null;
        Date l_dateTDOpen = null;
        Date l_dateTDMaturity = null;
        Date l_dateClosed = null;
        Date l_dateODLinkagesStart = null;
        Date l_dateODLinkagesEnd = null;
        Date l_dateTdLastRollover = null;
        Date l_datetdLastRedem = null;
        Date l_dateFirstDebitBookBal = null;
        Date l_dateFirstOverdrawn = null;
        Date l_dateLastPayment = null;
        Date l_nextWorking = null;
        Date l_currPeriodEnd = null;
        Date l_datetdTopUP = null;
        Date l_dateHist = null;
        Date l_pcEndDate = null;

        int 	l_atmDailyTransactionCount = 0,l_tenorTD = 0, l_daysCountSt = 0, l_daysCountEnd = 0,
                l_tenorRemainingTD = 0, l_count=0, l_recordCount = 0;
        Integer l_liabNo = 0;
        double l_overdueDays = 0.00;

        l_customerAccountId = ctiModelFrom_SEL_CASA_DATA.getCustAcNo();
        l_branch = ctiModelFrom_SEL_CASA_DATA.getBranchCode();
        l_product =	ctiModelFrom_SEL_CASA_DATA.getAccountClass();
        l_currency = ctiModelFrom_SEL_CASA_DATA.getCcy();
        l_accountBlocked = ctiModelFrom_SEL_CASA_DATA.getAcStatBlock();
        l_accountDormant = ctiModelFrom_SEL_CASA_DATA.getAcStatDormant();
        l_accountFrozen = ctiModelFrom_SEL_CASA_DATA.getAcStatFrozen();
        l_accountHasTovToday = ctiModelFrom_SEL_CASA_DATA.getHasTover();
        l_dateStart = ctiModelFrom_SEL_CASA_DATA.getAcOpenDate();
        l_amtATMDailyLimitAcy = ctiModelFrom_SEL_CASA_DATA.getAtmDailyAmtLimit();
        l_atmDailyTransactionCount = ctiModelFrom_SEL_CASA_DATA.getAtmDailyCountLimit();
        l_atmFacility = ctiModelFrom_SEL_CASA_DATA.getAtmFacility();
        l_amtAvailableBalanceAcy = ctiModelFrom_SEL_CASA_DATA.getAcyAvlBalance();
        l_amtCurrentBalanceAcy = ctiModelFrom_SEL_CASA_DATA.getAcyCurrBalance();
        l_amtCurrentBalanceLcy = ctiModelFrom_SEL_CASA_DATA.getLcyCurrBalance();

        log.debug("Mapping Dim Branch with EntityCode : {} and BranchCode : {} ",ctiModelFrom_SEL_CASA_DATA.getEntityCode(),ctiModelFrom_SEL_CASA_DATA.getBranchCode());
        DimBranchModel dimBranchModel = selectDimBrnLoader.dimBranchModel(ctiModelFrom_SEL_CASA_DATA.getEntityCode(),ctiModelFrom_SEL_CASA_DATA.getBranchCode());
        log.debug("DimBranchModel from SEL_DIM_BRANCH_LOADER query {}",dimBranchModel.toString());

        if(dimBranchModel != null){

            l_period = dimBranchModel.getCurrPeriod();
            l_year = dimBranchModel.getCurrYear();
            l_currPeriodEnd = dimBranchModel.getDateCurrPeriodEnd();
            l_applicationDate =dimBranchModel.getApplicationDate();
            l_dateHist = l_applicationDate;

        }
        log.debug("Mapping Dim Account with EntityCode : {} and BranchCode : {} and AccountNumber : {}");
        DimAccountModel dimAccountModel = selectCasaDimAccount.dimAccountModel(l_customerAccountId,l_branch,ctiModelFrom_SEL_CASA_DATA.getEntityCode());
        log.debug("DimAccountModel from SEL_CASA_DIM_ACCOUNT query {}",dimAccountModel.toString());


        if(dimAccountModel != null) {
            l_accountBrnKey = dimAccountModel.getAccountBrnKey();
        }
        else {
            l_accountBrnKey = null;
        }

        //Assigning current balance Acy as Credit balance.
        if (l_amtCurrentBalanceAcy > 0.00) {
            l_amtCreditBalAcy = l_amtCurrentBalanceAcy;
        }

        //Assigning current balance Acy as Debit balance if current balance is less then 0.
        if (l_amtCurrentBalanceAcy < 0.00) {
            l_amtDebitBalAcy = Math.abs(l_amtCurrentBalanceAcy);
        }

        l_amtBlockedAcy = ctiModelFrom_SEL_CASA_DATA.getAcyBlockedAmount();
        l_amtCrAccrualAcy = ctiModelFrom_SEL_CASA_DATA.getAcyAccruedCrIc();
        l_amtDrAccrualAcy = ctiModelFrom_SEL_CASA_DATA.getAcyAccruedDrIc();
        l_dateOfLastDrActivity = ctiModelFrom_SEL_CASA_DATA.getDateLastDrActivity();
        l_dateOfLastCrActivity = ctiModelFrom_SEL_CASA_DATA.getDateLastCrActivity();
        l_amtDaylightLimitAcy = ctiModelFrom_SEL_CASA_DATA.getDayLightLimitAmount();
        l_amtTodaysDrTovAcy = ctiModelFrom_SEL_CASA_DATA.getAcyTodayToverDr();
        l_amtTodaysDrTovLcy = ctiModelFrom_SEL_CASA_DATA.getLcyTodayToverDr();
        l_amtTodaysCrTovAcy = ctiModelFrom_SEL_CASA_DATA.getAcyTodayToverCr();
        l_amtTodaysCrTovLcy = ctiModelFrom_SEL_CASA_DATA.getLcyTodayToverCr();//need to check
        l_amtMtdCrTovAcy = ctiModelFrom_SEL_CASA_DATA.getAcyMtdToverCr();
        l_amtMtdCrTovLcy = ctiModelFrom_SEL_CASA_DATA.getLcyMtdToverCr();
        l_amtMtdDrTovAcy = ctiModelFrom_SEL_CASA_DATA.getAcyMtdToverDr();
        l_amtMtdDrTovLcy = ctiModelFrom_SEL_CASA_DATA.getLcyMtdToverDr();

        if(ctiModelFrom_SEL_CASA_DATA.getTodLimit() != 0.00) {
            l_amtTodLimitAcy = ctiModelFrom_SEL_CASA_DATA.getTodLimit();
        }
        else {
            l_amtTodLimitAcy = 0.00;
        }

        l_dateTODLimitStart = ctiModelFrom_SEL_CASA_DATA.getTodLimitStartDate();
        l_dateTODLimitEnd =	ctiModelFrom_SEL_CASA_DATA.getTodLimitEndDate();
        l_dateTODSince = ctiModelFrom_SEL_CASA_DATA.getTodSince();

        if(ctiModelFrom_SEL_CASA_DATA.getSubLimit() != 0.00) {
            l_amtSubLimitAcy = ctiModelFrom_SEL_CASA_DATA.getSubLimit();
        }

        else {
            l_amtSubLimitAcy=0.00;
        }

        l_odLimitLine =	ctiModelFrom_SEL_CASA_DATA.getLineId();
        l_amtUncollectedAcy = ctiModelFrom_SEL_CASA_DATA.getAcyUncollected();
        l_accountType =	ctiModelFrom_SEL_CASA_DATA.getAccountType();

        //Account Type 'Y' is TD account. Date open as starting of TD.
        if (l_accountType.equals("Y")) {
            l_dateTDOpen = l_dateStart;
        }

        log.debug("Loading SEL_CASA_CUST_INFO------------");











        Casa casaMart = new Casa();
        casaMart.setAccountBrnKey(ctiModelFrom_SEL_CASA_DATA.getBranchCode()+"--"+ctiModelFrom_SEL_CASA_DATA.getCustAcNo());
        casaMart.setEntityCode(ctiModelFrom_SEL_CASA_DATA.getEntityCode());
        casaMart.setAmtCurrentBalanceAcy(ctiModelFrom_SEL_CASA_DATA.getAcyCurrBalance());
        transformationQueue.setTransformationProcessStatus(ProcessStatus.PROCESSED);
        transformationQueue.setProcessedTimeStamp(new Timestamp(new Date().getTime()));
        CasaTransformationOutputModel casaTransformationOutputModel = new CasaTransformationOutputModel();
        casaTransformationOutputModel.setTransformationQueue(transformationQueue);
        casaTransformationOutputModel.setCasa(casaMart);

        log.debug("returning...ctoModel from CasaTransformationService....with {}",casaTransformationOutputModel.toString());
        return casaTransformationOutputModel;

    }
}
