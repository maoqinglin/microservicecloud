package com.ireadygo.springcloud.service;

import com.ireadygo.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {

    boolean add(Dept dept);

    Dept get(Long id);

    List<Dept> list();
}
