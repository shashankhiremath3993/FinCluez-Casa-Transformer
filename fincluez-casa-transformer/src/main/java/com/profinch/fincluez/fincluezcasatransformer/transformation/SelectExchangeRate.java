package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.ExchangeRateModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.ExchangeRateModelRowMapper;
import com.profinch.fincluez.finclueztlibrary.entities.infraEntities.QueryStore;
import com.profinch.fincluez.finclueztlibrary.entities.martEntities.TransformationQueue;
import com.profinch.fincluez.finclueztlibrary.service.QueryStoreCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;

public class SelectExchangeRate {

    private Logger log = LoggerFactory.getLogger(SelectBrnCcyCycleMap.class);

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;

    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    public ExchangeRateModel getExchangeRateModel(TransformationQueue transformationQueue){

       // log.debug("Inside....getSelectBrnCcyCycleMap...with TQ {}",transformationQueue.toString());


        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_EXCH_RATE);
        log.debug("Query Store for SEL_EXCH_RATE - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(stagingDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("branchCode" ,transformationQueue.getBranchCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,transformationQueue.getEntityCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("elRunDate" ,transformationQueue.getElRunDate());

        ExchangeRateModel exchangeRateModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new ExchangeRateModelRowMapper());
        log.debug("SelCasaCustPersonalInfo retrieved...is {}",exchangeRateModel.toString());

        return exchangeRateModel;
    }
}
