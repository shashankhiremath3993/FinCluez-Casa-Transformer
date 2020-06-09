package com.profinch.fincluez.fincluezcasatransformer.batch.step_1;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformerQueuePopulatorResponse;
import com.profinch.fincluez.finclueztlibrary.entities.infraEntities.queryStore.DriverQueryStore;
import com.profinch.fincluez.finclueztlibrary.repo.infraRepo.queryStore.DriverQueryStoreRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Configuration
public class CasaTransformerQueuePopulatorReaderConfig {


    private Logger log = LoggerFactory.getLogger(CasaTransformerQueuePopulatorReaderConfig.class);


    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    @Autowired
    private DriverQueryStoreRepo driverQueryStoreRepo;

    private DriverQueryStore getDriverQueryForQueuePopulator() {
        log.debug("Inside getDriverQueryForQueuePopulator");
        Optional<DriverQueryStore> driverQueryStoreOptional = driverQueryStoreRepo.findById(QueryStoreQueryId.SEL_CASA_TRANSFORMATION_DRIVER);
        if (driverQueryStoreOptional.isPresent()) {
            log.debug("driverquerystore retrieved {} ", driverQueryStoreOptional.get());

            return driverQueryStoreOptional.get();
        } else {
            log.debug("Querystore absent");
            return null;
        }

    }

    @Bean
    @StepScope
    public ItemReader<? extends CasaTransformerQueuePopulatorResponse> casaTransformerQueuePopulatorReader
            (@Value("#{jobParameters['entityCode']}") String entityCode,
             @Value("#{jobParameters['branchCode']}") String branchCode,
             @Value("#{jobParameters['elRunDate']}") Date elRunDate) {
        JdbcPagingItemReader<CasaTransformerQueuePopulatorResponse> reader = new JdbcPagingItemReader<CasaTransformerQueuePopulatorResponse>();
        final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(stagingDataSource);
        DriverQueryStore driverQueryStore = getDriverQueryForQueuePopulator();
        sqlPagingQueryProviderFactoryBean.setSelectClause(driverQueryStore.getSelectQueryString());
        sqlPagingQueryProviderFactoryBean.setFromClause(driverQueryStore.getFromQueryString());
        sqlPagingQueryProviderFactoryBean.setWhereClause(driverQueryStore.getWhereQueryString());
        sqlPagingQueryProviderFactoryBean.setSortKey(driverQueryStore.getSortQueryString());

        Map<String, Object> parameterValues = new HashMap<>();
        parameterValues.put("entityCode", entityCode);
        parameterValues.put("branchCode", branchCode);
        parameterValues.put("elRunDate", elRunDate);

        try {
            reader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());
            reader.setParameterValues(parameterValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        reader.setDataSource(stagingDataSource);
        reader.setPageSize(5);
        reader.setRowMapper(new BeanPropertyRowMapper<CasaTransformerQueuePopulatorResponse>(CasaTransformerQueuePopulatorResponse.class));
        return reader;

    }
}

