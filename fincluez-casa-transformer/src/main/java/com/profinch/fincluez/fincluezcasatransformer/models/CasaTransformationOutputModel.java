package com.profinch.fincluez.fincluezcasatransformer.models;

import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.Casa;
import com.profinch.fincluez.fincluezcasatransformer.entities.martEntities.CasaHist;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.transformer.TransformerOutputModel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Getter
@Setter
public class CasaTransformationOutputModel extends TransformerOutputModel {

    private Casa casa;
    private CasaHist casaHist;
    private TransformationQueue transformationQueue;



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}