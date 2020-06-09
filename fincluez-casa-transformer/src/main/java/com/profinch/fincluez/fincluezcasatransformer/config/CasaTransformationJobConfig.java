package com.profinch.fincluez.fincluezcasatransformer.config;

import com.profinch.fincluez.fincluezcasatransformer.batch.step_1.CasaTransformerQueuePopulatorProcessor;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_1.CasaTransformerQueuePopulatorWriter;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_2.CasaTransformationProcessor;
import com.profinch.fincluez.fincluezcasatransformer.batch.step_2.CasaTransformationWriter;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationOutputModel;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformerQueuePopulatorResponse;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@EnableBatchProcessing
@Configuration
public class CasaTransformationJobConfig {

    private Logger log = LoggerFactory.getLogger(CasaTransformationJobConfig.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    StepExecutionListener casaQueuePopulatorListener;
    @Autowired
    StepExecutionListener casaTransformationListener;

    //Job Listener
    @Autowired
    JobExecutionListener casaTransformationJobListener;

    //Step_0
    @Autowired
    private Tasklet validationTasklet;
    @Autowired
    private StepExecutionListener validationTaskletListener;

    //Step-1
    @Autowired
    private ItemReader casaTransformerQueuePopulatorReader;
    @Autowired
    private CasaTransformerQueuePopulatorProcessor casaTransformerQueuePopulatorProcessor;
    @Autowired
    private CasaTransformerQueuePopulatorWriter casaTransformerQueuePopulatorWriter;

    //Step-2
    @Autowired
    private ItemReader casaTransformationPagingReader;
    @Autowired
    private CasaTransformationProcessor casaTransformationProcessor;
    @Autowired
    private CasaTransformationWriter casaTransformationWriter;


    public CasaTransformationJobConfig() {
        log.info("Creating beans for FinCluez CasaTransformationService BatchProcessorJob!!");
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("FinCluez-CasaTransformer-");
    }

    //Step-0
    @Bean
    Step validationTaskletStep() {
        return stepBuilderFactory.get("validationTaskletStep")
                .tasklet(validationTasklet)
                .listener(validationTaskletListener)
                .build();
    }

    //Step-1
    @Bean
    Step casaTransformerQueuePopulatorStep() {
        log.debug("Creating casaTransformerQueuePopulatorStep");
        SimpleStepBuilder builder =
                (SimpleStepBuilder) stepBuilderFactory.get("casaTransformerQueuePopulatorStep")
                        .<CasaTransformerQueuePopulatorResponse, TransformationQueue>chunk(5)
                        .reader(casaTransformerQueuePopulatorReader)
                        .processor(casaTransformerQueuePopulatorProcessor)
                        .writer(casaTransformerQueuePopulatorWriter)
                        .taskExecutor(taskExecutor())
                        .throttleLimit(2);
        builder.listener((StepExecutionListener) casaQueuePopulatorListener);
        return builder.build();
    }


    //Step-2
    @Bean
    Step casaTransformationStep(
            @Qualifier("validationTaskletStep") Step validationTaskletStep,
            @Qualifier("casaTransformationQueuePopulatorStep") Step casaTransformationQueuePopulatorStep,
            @Qualifier("casaTransformationStep") Step casaTransformationStep
    ) {
        log.debug("Creating casaTransformationStep");
        SimpleStepBuilder builder = (SimpleStepBuilder) stepBuilderFactory.get("casaTransformationStep")
                .<TransformationQueue, CasaTransformationOutputModel>chunk(5)
                .reader(casaTransformationPagingReader)
                .processor(casaTransformationProcessor)
                .writer(casaTransformationWriter)
                .taskExecutor(taskExecutor())
                .throttleLimit(2);
        builder.listener((StepExecutionListener) casaTransformationListener);
        return builder.build();
    }


    @Bean
    public Job casaTransformationJob(
            @Qualifier("validationTaskletStep") Step validationTaskletStep,
            @Qualifier("casaTransformationQueuePopulatorStep") Step casaTransformerQueuePopulatorStep,
            @Qualifier("casaTransformationStep") Step casaTransformationStep
    ) {
        log.debug("Creating casaTransformerQueuePopulatorJob");
        return jobBuilderFactory.get("casaTransformationBatchProcessJob")
                .incrementer(new RunIdIncrementer())
                //Step-0 to Step-1
                .start(validationTaskletStep)
                .on("run-Step-1")
                .to(casaTransformerQueuePopulatorStep)
                .from(validationTaskletStep).on("skip-Step-1")
                .to(casaTransformationStep)
                .from(casaTransformerQueuePopulatorStep)
                .on("*")
                .to(casaTransformationStep)
                .end()
                .listener(casaTransformationJobListener)
                .build();
    }

}
