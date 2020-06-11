package com.profinch.fincluez.fincluezcasatransformer.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.sql.Date;

@Getter
@Setter
public class TransformationQueueModel {

    private String entity_code;
    private String branch_code;
    private String cust_ac_no;
    private Date el_run_date;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
