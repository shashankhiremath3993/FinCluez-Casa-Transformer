package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.fincluezcasatransformer.constants.JobName;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.jobStatus.TransformationJobStatus;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.jobStatus.TransformationJobStatusRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

public class CasaTransformerQueuePopulatorListener implements StepExecutionListener {
    private Logger log = LoggerFactory.getLogger(CasaTransformerQueuePopulatorListener.class);

    @Autowired
    private TransformationJobStatusRepo transformationJobStatusRepo;

    @Override
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.debug("********* BEFORE STEP-1");
    }

    @Override
    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("********* AFTER STEP-1");
        Date elRunDate = stepExecution.getJobParameters().getDate("elRunDate");
        log.debug("********* AFTER STEP--1....with EL-Run-Date as {}", elRunDate);

        TransformationJobStatus transformationJobStatus = new TransformationJobStatus();
        transformationJobStatus.setJobName(JobName.CASA_TRANSFORMATION_JOB.toString());
        transformationJobStatus.setStepName(JobName.CASA_TRANSFORMATION_QUEUE_POPULATOR_STEP.toString());
        transformationJobStatus.setElRunDate(elRunDate);
        transformationJobStatus.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transformationJobStatusRepo.save(transformationJobStatus);
        log.debug("Transformation Job Status -- written in After STEP-1 *** CasaTransformationQueuePopulatorStep");
        return ExitStatus.COMPLETED;
    }
}
