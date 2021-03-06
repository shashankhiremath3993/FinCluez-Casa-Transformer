package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.fincluezcasatransformer.constants.JobName;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationJobStatus;
import com.profinch.fincluez.finclueztlibrary.repo.martRepo.TransformationJobStatusRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class CasaTransformationQueuePopulatorListener implements StepExecutionListener {
    private Logger log = LoggerFactory.getLogger(CasaTransformationQueuePopulatorListener.class);

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
        log.debug("********* AFTER STEP-1...first occurrence");
        Date elRunDate = stepExecution.getJobParameters().getDate("elRunDate");
        String branchCode = stepExecution.getJobParameters().getString("branchCode");
        String entityCode = stepExecution.getJobParameters().getString("entityCode");

        log.debug("********* AFTER STEP--1....with EL-Run-Date as {}", elRunDate);
        log.debug("********* AFTER STEP--1....with EntityCode as {}", entityCode);
        log.debug("********* AFTER STEP--1....with BranchCode as {}", branchCode);

        TransformationJobStatus transformationJobStatus = new TransformationJobStatus();
        transformationJobStatus.setJobName(JobName.CASA_TRANSFORMATION_JOB.toString());
        transformationJobStatus.setStepName(JobName.CASA_TRANSFORMATION_QUEUE_POPULATOR_STEP.toString());
        transformationJobStatus.setElRunDate(elRunDate);
        transformationJobStatus.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transformationJobStatusRepo.save(transformationJobStatus);
        log.debug("Transformation Job Status -- written in After STEP-1 *** CasaTransformationQueuePopulatorStep");
        log.debug("Printing After STEP-1 stepExecution.exitStatus {}",stepExecution.getExitStatus());
        return ExitStatus.COMPLETED;
    }
}
