package com.ireadygo.springcloud.controller;

import com.ireadygo.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

//    public static final String REST_URL_PREFIX = "http://localhost:8001"; 不需要端口
    public static final String REST_URL_PREFIX = "http://microservicecloud-dept";

    @Autowired
    RestTemplate restTemplate;

//    使用Ribbon负载均衡后，会依赖jackson-dataformat-xml这个依赖，导致返回数据为xml
    @RequestMapping(value = "/consumer/dept/get/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }

    @RequestMapping("/consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }
}
