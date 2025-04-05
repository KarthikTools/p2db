package com.example.dbservice.dto;

import lombok.Data;

@Data
public class DbRequest {
    private String host;                   
    private int port;                      
    private String user;                   
    private String password;               
    private boolean trustServerCertificate;
    private String database;               
    private String sql;                    
} 