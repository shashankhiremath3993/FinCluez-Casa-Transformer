package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Getter
@Setter
public class ExchangeRateModel {

    private String branchCode;
    private String currency1;
    private String currency2;
    private double midRate;
    private double powerFactor;
    private String rateType;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
