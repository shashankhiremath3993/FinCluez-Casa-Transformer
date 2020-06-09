package com.profinch.fincluez.fincluezcasatransformer.entities.stagingEntities;


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
@IdClass(StgSttmCustAccountCK.class)
public class StgSttmCustAccount implements Serializable {
    @Id
    private String branchCode;
    @Id
    private String customerAccountNumber;
    @Id
    private Date elRunDate;
    @Id
    private String entityCode;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}