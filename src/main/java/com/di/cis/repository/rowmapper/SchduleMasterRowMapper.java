package com.di.cis.repository.rowmapper;

import com.di.cis.domain.SchduleMaster;
import io.r2dbc.spi.Row;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link SchduleMaster}, with proper type conversions.
 */
@Service
public class SchduleMasterRowMapper implements BiFunction<Row, String, SchduleMaster> {

    private final ColumnConverter converter;

    public SchduleMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link SchduleMaster} stored in the database.
     */
    @Override
    public SchduleMaster apply(Row row, String prefix) {
        SchduleMaster entity = new SchduleMaster();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setInstallmentNumber(converter.fromRow(row, prefix + "_installment_number", Integer.class));
        entity.setReducingBalance(converter.fromRow(row, prefix + "_reducing_balance", BigDecimal.class));
        entity.setPrincipleAmount(converter.fromRow(row, prefix + "_principle_amount", BigDecimal.class));
        entity.setInterest(converter.fromRow(row, prefix + "_interest", BigDecimal.class));
        entity.setTotalInstallment(converter.fromRow(row, prefix + "_total_installment", BigDecimal.class));
        entity.setDueDate(converter.fromRow(row, prefix + "_due_date", Long.class));
        entity.setRemarks(converter.fromRow(row, prefix + "_remarks", String.class));
        return entity;
    }
}
