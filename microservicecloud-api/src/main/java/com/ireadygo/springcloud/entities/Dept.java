package com.ireadygo.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

// 必须实现序列化

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable {

    private Long deptno;    // 主键
    private String dname;   // 部门名称
    private String db_source;   // 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息可以被存储到不同数据库

    public Dept(String dname) {
        super();
        this.dname = dname;
    }
}
