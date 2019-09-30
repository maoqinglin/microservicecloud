package com.ireadygo.springcloud.service;

import com.ireadygo.springcloud.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

// 必须注入容器，统一处理异常，实现服务降级，以后其它的客户端不会调用服务接口
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("该ID："+id+" 没有对应的信息，Consumer客户端提供降级信息，此刻服务Provider已经关闭").setDb_source("no data in MySQL");
            }

            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
