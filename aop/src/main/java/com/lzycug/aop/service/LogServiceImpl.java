
package com.lzycug.aop.service;

import com.lzycug.aop.dao.LogDao;
import com.lzycug.aop.pojo.SysLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description：日志记录的业务实现
 * author：lzyCug
 * date: 2020/4/5 22:40
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;

    @Override
    public boolean insert(SysLog sysLog) {
        Integer result = logDao.insert(sysLog);
        if (result > 0) {
            return true;
        }
        return false;
    }
}
