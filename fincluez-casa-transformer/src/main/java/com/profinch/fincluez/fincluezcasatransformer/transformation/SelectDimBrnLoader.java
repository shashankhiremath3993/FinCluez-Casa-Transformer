package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.constants.QueryStoreQueryId;
import com.profinch.fincluez.fincluezcasatransformer.models.DimBranchModel;
import com.profinch.fincluez.fincluezcasatransformer.rowMapper.DimBranchModelRowMapper;
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
public class SelectDimBrnLoader {
    private Logger log = LoggerFactory.getLogger(SelectDimBrnLoader.class);

    @Autowired
    private QueryStoreCacheService queryStoreCacheService;


    @Autowired
    @Qualifier("martDataSource")
    private DataSource martDataSource;

    public DimBranchModel getDimBranchModel(String entityCode,String branchCode){
        log.debug("Inside....dimBranchModel...with entityCode : {}  and BranchCode : {}",entityCode,branchCode);

        QueryStore queryStore = queryStoreCacheService.getQueryStoreByIdQuery(QueryStoreQueryId.SEL_DIM_BRN_LOADER);
        log.debug("Query Store for SEL_DIM_BRN_LOADER - retrieved {}",queryStore.getQueryString());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(martDataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        ((MapSqlParameterSource) sqlParameterSource).addValue("branchCode" ,branchCode);
        ((MapSqlParameterSource) sqlParameterSource).addValue("entityCode" ,entityCode);

        DimBranchModel dimBranchModel = namedParameterJdbcTemplate.queryForObject(queryStore.getQueryString(),sqlParameterSource,new DimBranchModelRowMapper());
        log.debug("dimBranchModel retrieved...is {}",dimBranchModel.toString());

        return dimBranchModel;


    }


}
