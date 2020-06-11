package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CTIModelRowMapper implements RowMapper<CasaTransformationInputModel> {
    @Override
    public CasaTransformationInputModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        CasaTransformationInputModel casaTransformationInputModel = new CasaTransformationInputModel();

        casaTransformationInputModel.setBranchCode(rs.getString("branchCode"));
        casaTransformationInputModel.setEntityCode(rs.getString("entityCode"));
        casaTransformationInputModel.setCustAcNo(rs.getString("custAcNo"));
        casaTransformationInputModel.setAccountClass(rs.getString("accountClass"));
        casaTransformationInputModel.setAcyCurrBalance(rs.getDouble("acyCurrBalance"));
        return casaTransformationInputModel;
    }
}
