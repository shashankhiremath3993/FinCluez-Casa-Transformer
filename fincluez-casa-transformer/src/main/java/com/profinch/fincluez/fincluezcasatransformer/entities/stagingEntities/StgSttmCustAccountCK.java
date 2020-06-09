package com.profinch.fincluez.fincluezcasatransformer.entities.stagingEntities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class StgSttmCustAccountCK implements Serializable {

    private String branchCode;
    private String customerAccountNumber;
    private Date elRunDate;
    private String entityCode;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}