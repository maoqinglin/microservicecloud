package com.ireadygo.springcloud.controller;

import com.ireadygo.springcloud.entities.Dept;
import com.ireadygo.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {  // 必须加 @RequestBody
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id) {
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.list();
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> serviceNames = discoveryClient.getServices();
        System.out.println("**************" + serviceNames);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("microservicecloud-dept");
        for (ServiceInstance serviceInstance : serviceInstances) {
            System.out.println(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" +
                    serviceInstance.getPort() + "\t" + serviceInstance.getUri() + "\t");
        }
        return this.discoveryClient;
    }
}
