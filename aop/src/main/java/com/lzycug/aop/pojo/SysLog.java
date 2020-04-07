
package com.lzycug.aop.pojo;

import lombok.Data;

import java.util.Date;

/**
 * description：日志记录的实体类
 * author：lzyCug
 * date: 2020/4/5 22:37
 */
@Data
public class SysLog {
    // 访问记录ID
    private String id;

    // 访问时间
    private Date visitTime;

    // 访问的IP
    private String ip;

    // 访问的地址
    private String url;

    // 访问耗时（毫秒）
    private Long executionTime;

    // 访问的方法名称
    private String method;
}
