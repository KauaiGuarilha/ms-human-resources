package com.demo.hrworker.controller;

import com.demo.hrworker.model.parser.WorkerParser;
import com.demo.hrworker.model.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("workers")
public class WorkerController {

    @Value("${test.config}")
    private String testConfig;

    @Autowired private WorkerService service;
    @Autowired private WorkerParser parser;

    @GetMapping("/config")
    public ResponseEntity<Void> config(){
        System.out.println(testConfig);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        return new ResponseEntity<>(parser.toResponse(service.workerById(id)), HttpStatus.OK);
    }
}
