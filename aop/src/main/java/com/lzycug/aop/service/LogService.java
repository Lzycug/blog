package com.lzycug.aop.service;

import com.lzycug.aop.pojo.SysLog;

/**
 * description：日志记录的业务接口
 * author：lzyCug
 * date: 2020/4/5 22:36
 */
public interface LogService {
    boolean insert(SysLog sysLog);
}
