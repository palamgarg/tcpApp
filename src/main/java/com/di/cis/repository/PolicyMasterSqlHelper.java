package com.di.cis.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PolicyMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("purpuse_name", table, columnPrefix + "_purpuse_name"));
        columns.add(Column.aliased("policy_name", table, columnPrefix + "_policy_name"));
        columns.add(Column.aliased("charges_type", table, columnPrefix + "_charges_type"));
        columns.add(Column.aliased("interest_rate", table, columnPrefix + "_interest_rate"));
        columns.add(Column.aliased("number_of_installments", table, columnPrefix + "_number_of_installments"));
        columns.add(Column.aliased("penalty_rate_of_interest", table, columnPrefix + "_penalty_rate_of_interest"));
        columns.add(Column.aliased("installment_duration", table, columnPrefix + "_installment_duration"));

        return columns;
    }
}
