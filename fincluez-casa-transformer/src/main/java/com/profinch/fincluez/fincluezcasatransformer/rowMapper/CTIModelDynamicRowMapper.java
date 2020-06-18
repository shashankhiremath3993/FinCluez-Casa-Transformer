package com.profinch.fincluez.fincluezcasatransformer.rowMapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CTIModelDynamicRowMapper<T> extends BeanWrapperFieldSetMapper<T> implements RowMapper<T> {

    @Override
    public T mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final FieldSet fs = getFieldSet(rs);
        try {
            return super.mapFieldSet(fs);
        } catch (org.springframework.validation.BindException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FieldSet getFieldSet(final ResultSet rs) throws SQLException {
        final ResultSetMetaData metaData = rs.getMetaData();
        final int columnCount = metaData.getColumnCount();

        final List<String> tokens = new ArrayList<>();
        final List<String> names = new ArrayList<>();

        for (int i = 1; i <= columnCount; i++) {
            tokens.add(rs.getString(i));
            names.add(metaData.getColumnName(i));
        }

        return new DefaultFieldSet(tokens.toArray(new String[0]), names.toArray(new String[0]));
    }

}
