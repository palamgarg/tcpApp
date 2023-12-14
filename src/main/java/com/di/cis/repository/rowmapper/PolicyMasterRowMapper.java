package com.di.cis.repository.rowmapper;

import com.di.cis.domain.PolicyMaster;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link PolicyMaster}, with proper type conversions.
 */
@Service
public class PolicyMasterRowMapper implements BiFunction<Row, String, PolicyMaster> {

    private final ColumnConverter converter;

    public PolicyMasterRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PolicyMaster} stored in the database.
     */
    @Override
    public PolicyMaster apply(Row row, String prefix) {
        PolicyMaster entity = new PolicyMaster();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPurpuseName(converter.fromRow(row, prefix + "_purpuse_name", String.class));
        entity.setPolicyName(converter.fromRow(row, prefix + "_policy_name", String.class));
        entity.setChargesType(converter.fromRow(row, prefix + "_charges_type", String.class));
        entity.setInterestRate(converter.fromRow(row, prefix + "_interest_rate", Double.class));
        entity.setNumberOfInstallments(converter.fromRow(row, prefix + "_number_of_installments", Integer.class));
        entity.setPenaltyRateOfInterest(converter.fromRow(row, prefix + "_penalty_rate_of_interest", Double.class));
        entity.setInstallmentDuration(converter.fromRow(row, prefix + "_installment_duration", Integer.class));
        return entity;
    }
}
