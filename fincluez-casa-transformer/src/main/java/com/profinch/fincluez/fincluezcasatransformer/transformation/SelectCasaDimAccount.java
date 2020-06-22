package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.DimAccountModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.DimAccountModelRowMapper;
import com.profinch.fincluez.finclueztlibrary.entities.infraEntities.QueryStore;
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
public class SelectCasaDimAccount {
    private Logger log = LoggerFactory.getLogger(SelectDimBrnLoader.class);

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;


    @Autowired
    @Qualifier("martDataSource")
    private DataSource martDataSource;

    public DimAccountModel getDimAccountModel(String account, String branch,String entityCode){
        log.debug("Inside....dimAccountModel...with account : {}  and branch : {} and entityCode : {} ",account,branch,entityCode);

        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_CASA_DIM_ACCOUNT);
        log.debug("Query Store for SEL_CASA_DIM_ACCOUNT - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(martDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("branch" ,branch);
        ((MapSqlParameterSource) sqlParameterSource).addValue("account" ,account);
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,entityCode);


        DimAccountModel dimAccountModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new DimAccountModelRowMapper());
        log.debug("dimBranchModel retrieved...is {}",dimAccountModel.toString());

        return dimAccountModel;


    }


}
