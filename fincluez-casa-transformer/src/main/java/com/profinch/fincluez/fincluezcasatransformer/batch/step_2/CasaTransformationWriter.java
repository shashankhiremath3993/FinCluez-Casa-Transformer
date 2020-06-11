package com.profinch.fincluez.fincluezcasatransformer.batch.step_2;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationOutputModel;
import com.profinch.fincluez.fincluezcasatransformer.repo.martRepo.CasaHistRepo;
import com.profinch.fincluez.fincluezcasatransformer.repo.martRepo.CasaRepo;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.TransformationQueueRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class CasaTransformationWriter implements ItemWriter<CasaTransformationOutputModel> {

    private Logger log = LoggerFactory.getLogger(CasaTransformationWriter.class);


    private CasaRepo casaRepo;
    private CasaHistRepo casaHistRepo;
    private TransformationQueueRepo transformationQueueRepo;


    @Autowired
    public CasaTransformationWriter(CasaRepo casaRepo,CasaHistRepo casaHistRepo,
                                    TransformationQueueRepo transformationQueueRepo) {
        this.casaRepo = casaRepo;
        this.casaHistRepo = casaHistRepo;
        this.transformationQueueRepo = transformationQueueRepo;

    }

    @Override
    public void write(List<? extends CasaTransformationOutputModel> casaTransformationOutputModelsList) throws Exception {
        log.debug("Inside CasaTransformationOutputModel Writer with {}", casaTransformationOutputModelsList.size());
        for (CasaTransformationOutputModel casaTransformationOutputModel : casaTransformationOutputModelsList){
            log.debug("Repo Saving Mart {}", casaTransformationOutputModel.getCasa());

            if (casaTransformationOutputModel.getCasa() != null){
                log.debug("Casa in CTOModel is not null");
                casaRepo.save(casaTransformationOutputModel.getCasa());
            }


            log.debug("Repo Saving Mart Hist {}", casaTransformationOutputModel.getCasaHist());
            if (casaTransformationOutputModel.getCasaHist() != null){
                log.debug("CasaHist in CTOModel is not null");
                casaHistRepo.save(casaTransformationOutputModel.getCasaHist());
            }

            log.debug("Persisting TransformationQueue {}", casaTransformationOutputModel.getTransformationQueue());
            transformationQueueRepo.save(casaTransformationOutputModel.getTransformationQueue());
        }
        log.debug("written CasaTransformationOutputModel");
    }

}
