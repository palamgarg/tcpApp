package com.di.cis.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class SchduleMasterSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("installment_number", table, columnPrefix + "_installment_number"));
        columns.add(Column.aliased("reducing_balance", table, columnPrefix + "_reducing_balance"));
        columns.add(Column.aliased("principle_amount", table, columnPrefix + "_principle_amount"));
        columns.add(Column.aliased("interest", table, columnPrefix + "_interest"));
        columns.add(Column.aliased("total_installment", table, columnPrefix + "_total_installment"));
        columns.add(Column.aliased("due_date", table, columnPrefix + "_due_date"));
        columns.add(Column.aliased("remarks", table, columnPrefix + "_remarks"));

        return columns;
    }
}
