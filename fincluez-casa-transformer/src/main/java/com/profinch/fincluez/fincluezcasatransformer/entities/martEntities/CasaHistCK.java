package com.profinch.fincluez.fincluezcasatransformer.entities.martEntities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class CasaHistCK implements Serializable {

    private String accountBrnKey;
    private String entityCode;
    private Date dateHist;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
