package com.example.dbservice.controller;

import com.example.dbservice.dto.DbRequest;
import com.example.dbservice.service.DbQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class QueryController {

    @Autowired
    private DbQueryService dbQueryService;

    @PostMapping("/query")
    public Map<String, Object> runQuery(@RequestBody DbRequest dbRequest) throws Exception {
        return dbQueryService.executeDynamicQuery(dbRequest);
    }
} 