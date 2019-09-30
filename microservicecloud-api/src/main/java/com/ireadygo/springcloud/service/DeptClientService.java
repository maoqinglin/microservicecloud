package com.ireadygo.springcloud.service;

import com.ireadygo.springcloud.entities.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// Rest 负载均衡
//@FeignClient(value = "microservicecloud-dept")
@FeignClient(value = "microservicecloud-dept", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    //    请求服务 Rest 接口的封装
    @RequestMapping(value = "/dept/get/{id}")
    Dept get(@PathVariable("id") Long id);

    @RequestMapping("/dept/add")
    boolean add(Dept dept);

    @RequestMapping(value = "/dept/list")
    List<Dept> list();
}
