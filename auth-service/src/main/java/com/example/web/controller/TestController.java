package com.example.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    private final LoadBalancerClient loadBalancerClient;

    @Value("${test}")
    private String test;

    @GetMapping("helloEureka")
    public ResponseEntity<String> helloEureka() {

//        URI serviceInstance = loadBalancerClient.choose("user-service").getUri();
//        return ResponseEntity.status(HttpStatus.OK).body(
//                restTemplate.getForObject(serviceInstance+ "/api/v1/users/1", String.class)
//        );

        return ResponseEntity.ok(test);

    }

}
