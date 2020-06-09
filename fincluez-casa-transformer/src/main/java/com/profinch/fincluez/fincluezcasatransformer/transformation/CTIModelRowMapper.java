package com.profinch.fincluez.fincluezcasatransformer.transformation;

import com.profinch.fincluez.fincluezcasatransformer.models.CasaTransformationInputModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CTIModelRowMapper implements RowMapper<CasaTransformationInputModel> {
    @Override
    public CasaTransformationInputModel mapRow(ResultSet rs, int rowNum) throws SQLException {
       CasaTransformationInputModel casaTransformationInputModel = new CasaTransformationInputModel();
       casaTransformationInputModel.setAccountClass(rs.getString("account_class"));

        return casaTransformationInputModel;
    }
}
