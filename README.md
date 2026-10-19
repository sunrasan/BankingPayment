# Banking Payment Processing API — Concierto Modernize Test

A realistic, intentionally small Java 8 backend for assessment/modernization testing. It models an internal bank transfer and includes three Oracle tables, two views, a PL/SQL package with procedures, JPA/Hibernate mappings and JDBC procedure calls.

## Technology
- Java 8
- Spring Boot 2.7.18
- Spring Web REST API
- Spring Data JPA / Hibernate ORM
- Spring JDBC `SimpleJdbcCall`
- Oracle JDBC `ojdbc8`
- Oracle PL/SQL package and views

## Database objects
Tables (exactly 3): `CUSTOMER`, `BANK_ACCOUNT`, `PAYMENT_TXN`
Views: `V_ACCOUNT_SUMMARY`, `V_PAYMENT_DETAILS`
Sequences: `CUSTOMER_SEQ`, `BANK_ACCOUNT_SEQ`, `PAYMENT_TXN_SEQ`
Package: `BANKING_API_PKG`
Procedures: `TRANSFER_FUNDS`, `GET_ACCOUNT_SUMMARY`, `COUNT_PAYMENTS`

## Setup
1. Use JDK 8 and Maven 3.6+.
2. Connect to Oracle as the app schema user.
3. Run `database/oracle_ddl.sql`.
4. Run `database/oracle_package.sql`.
5. Update `src/main/resources/application.properties` with your Oracle connection.
6. Run:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

Default JDBC URL uses Oracle Free service `FREEPDB1`; update it for your database.

## API examples
List accounts using JPA:
```bash
curl http://localhost:8080/api/accounts
```

Account details using JPA:
```bash
curl http://localhost:8080/api/accounts/5001
```

Account summary through PL/SQL package procedure:
```bash
curl http://localhost:8080/api/accounts/5001/summary-procedure
```

Transfer funds through PL/SQL package procedure:
```bash
curl -X POST http://localhost:8080/api/payments/transfer \
  -H "Content-Type: application/json" \
  -d '{"fromAccountId":5001,"toAccountId":5002,"amount":1250.00,"referenceText":"DEMO-TRANSFER-001"}'
```

Get account payment history using JPA:
```bash
curl http://localhost:8080/api/payments/account/5001
```

## Concierto Modernize scan targets
- Java 8 and Spring Boot 2.7 dependencies
- JPA entity-to-table/foreign-key relationships
- Oracle JDBC driver and JDBC stored procedure calls
- PL/SQL package, stored procedures, sequences and views
- Oracle-specific SQL, `RAISE_APPLICATION_ERROR`, `SELECT ... FOR UPDATE`, `SYSTIMESTAMP`, `DUAL`
- REST controllers and service/database coupling

## Important limitations
This is a demonstration scaffold, not production banking software. It lacks authentication/authorization, idempotency keys, audit/security controls, limits, monitoring, reconciliation, and formal integration tests. Validate the application against your Oracle version before using it in a demo. The stored procedure intentionally does not COMMIT; transaction ownership remains with the caller/session. For a production system, explicitly define transaction boundaries and rollback behavior in the integration layer.
