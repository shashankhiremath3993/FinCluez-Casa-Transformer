package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.BranchCcyCycleModel;
import com.profinch.fincluez.fincluezcasatransformer.models.CasaModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.BranchCcyCycleModelRowMapper;
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
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SelectBrnCcyCycleMap {
    private Logger log = LoggerFactory.getLogger(SelectBrnCcyCycleMap.class);

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;

    @Autowired
    @Qualifier("stagingDataSource")
    private DataSource stagingDataSource;

    public BranchCcyCycleModel getSelectBrnCcyCycleMap(TransformationQueue transformationQueue){

        log.debug("Inside....getSelectBrnCcyCycleMap...with TQ {}",transformationQueue.toString());


        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_CASA_BRN_CURR_CYCL_MAP);
        log.debug("Query Store for SEL_CASA_BRN_CURR_CYCL_MAP - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(stagingDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("branchCode" ,transformationQueue.getBranchCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,transformationQueue.getEntityCode());
        ((MapSqlParameterSource) sqlParameterSource).addValue("elRunDate" ,transformationQueue.getElRunDate());

        BranchCcyCycleModel branchCcyCycleModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new BranchCcyCycleModelRowMapper());
        log.debug("SelCasaCustPersonalInfo retrieved...is {}",branchCcyCycleModel.toString());

        return branchCcyCycleModel;
    }


}
