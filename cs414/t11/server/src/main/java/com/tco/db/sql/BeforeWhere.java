package com.tco.db.sql;

public class BeforeWhere {
    public ExpressionClause where(String lhs) {
        return ExpressionClause.where(this.toString(), lhs);
    }

    public ExpressionClause whereNot(String lhs) {
        return ExpressionClause.whereNot(this.toString(), lhs);
    }

    @Override
    public String toString() {
        return "";
    }
}
