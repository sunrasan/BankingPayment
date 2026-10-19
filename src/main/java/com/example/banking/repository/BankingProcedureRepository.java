package com.example.banking.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BankingProcedureRepository {
    private final JdbcTemplate jdbcTemplate;

    public BankingProcedureRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long transferFunds(Long fromAccountId, Long toAccountId,
                              BigDecimal amount, String reference) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("BANKING_API_PKG")
                .withProcedureName("TRANSFER_FUNDS")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_FROM_ACCOUNT_ID", Types.NUMERIC),
                        new SqlParameter("P_TO_ACCOUNT_ID", Types.NUMERIC),
                        new SqlParameter("P_AMOUNT", Types.NUMERIC),
                        new SqlParameter("P_REFERENCE_TEXT", Types.VARCHAR),
                        new SqlOutParameter("P_PAYMENT_ID", Types.NUMERIC)
                );

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("P_FROM_ACCOUNT_ID", fromAccountId);
        input.put("P_TO_ACCOUNT_ID", toAccountId);
        input.put("P_AMOUNT", amount);
        input.put("P_REFERENCE_TEXT", reference);
        Map<String, Object> output = call.execute(input);
        Object paymentId = output.get("P_PAYMENT_ID");
        return paymentId == null ? null : ((Number) paymentId).longValue();
    }

    public Map<String, Object> getAccountSummary(Long accountId) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("BANKING_API_PKG")
                .withProcedureName("GET_ACCOUNT_SUMMARY")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_ACCOUNT_ID", Types.NUMERIC),
                        new SqlOutParameter("P_ACCOUNT_NUMBER", Types.VARCHAR),
                        new SqlOutParameter("P_CUSTOMER_NAME", Types.VARCHAR),
                        new SqlOutParameter("P_CURRENCY_CODE", Types.VARCHAR),
                        new SqlOutParameter("P_AVAILABLE_BALANCE", Types.NUMERIC),
                        new SqlOutParameter("P_ACCOUNT_STATUS", Types.VARCHAR)
                );

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("P_ACCOUNT_ID", accountId);
        return call.execute(input);
    }
}
