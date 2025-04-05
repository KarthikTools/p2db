package com.example.dbservice.service;

import com.example.dbservice.dto.DbRequest;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class DbQueryService {

    public Map<String, Object> executeDynamicQuery(DbRequest req) throws SQLException {
        String connectionUrl = String.format(
            "jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=%s",
            req.getHost(),
            req.getPort(),
            req.getDatabase(),
            req.isTrustServerCertificate()
        );

        try (Connection conn = DriverManager.getConnection(connectionUrl, req.getUser(), req.getPassword());
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(req.getSql());
            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            List<Map<String, Object>> rows = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= colCount; i++) {
                    String colName = meta.getColumnName(i);
                    Object val = rs.getObject(i);
                    row.put(colName, val);
                }
                rows.add(row);
            }

            Map<String, Object> result = new HashMap<>();
            result.put("rows", rows);
            return result;
        }
    }
} 