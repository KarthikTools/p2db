package com.example.dbservice.dto;

public class DbRequest {
    private String host;
    private int port;
    private String user;
    private String password;
    private boolean trustServerCertificate;
    private String database;
    private String sql;

    // No-args constructor
    public DbRequest() {}

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTrustServerCertificate() {
        return trustServerCertificate;
    }
    public void setTrustServerCertificate(boolean trustServerCertificate) {
        this.trustServerCertificate = trustServerCertificate;
    }

    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }

    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
}
