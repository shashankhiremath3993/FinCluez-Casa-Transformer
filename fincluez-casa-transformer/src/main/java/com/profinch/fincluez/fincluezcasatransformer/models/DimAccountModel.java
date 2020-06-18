package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


import java.util.Date;


@Getter
@Setter
public class DimAccountModel {

    private String entityCode;
    private String accountBrnKey;
    private String account;
    private String accountDesc;
    private String branchKey;
    private String branch;
    private String productKey;
    private String currencyKey;
    private String customerKey;
    private String accountTypeKey;
    private String accountStatusKey;
    private Date dateAccountOpen;
    private Date dateStart;
    private String acctCountryKey;
    private String alternateAccountNo;
    private String derivedStatusKey;
    private Integer dormancyDays;
    private Date dateDormant;
    private String exposureCategory;
    private String fundId;
    private String ibanAccountNumber;
    private String modeOfOperation;
    private String noCrAllowed;
    private String noDrAllowed;
    private String passbookFacility;
    private String previousTodSince;
    private String accRecordStat;
    private String salaryAccount;
    private Date dateStatusSince;
    private String chequeBookFacility;
    private Integer chequeRejectionCount;
    private String clearingAccountNumber;
    private String clearingBankCode;
    private String drGl;
    private String crGl;
    private String location;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
