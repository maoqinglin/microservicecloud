package com.ireadygo.springcloud.controller;

import com.ireadygo.springcloud.entities.Dept;
import com.ireadygo.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer_Feign {

    //    集成了 Feign后，使用 DeptClientService 替换 RestTemplate 请求服务
    @Autowired
    DeptClientService deptClientService;

    @RequestMapping(value = "/consumer/dept/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Dept get(@PathVariable("id") Long id) {
        return deptClientService.get(id);
    }

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Dept> list() {
        return deptClientService.list();
    }

}
