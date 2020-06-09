package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Getter
@Setter
public class MaintDefaultCalculatorModel {

    private String maintCase;// Changed name here as "case" is a java keyword
    private String module;
    private String status;
    private Integer overdueDays;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
