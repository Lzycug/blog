
package com.lzycug.aop.dao;

import com.lzycug.aop.pojo.SysLog;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * description：日志记录的数据库操作接口
 * author：lzyCug
 * date: 2020/4/5 22:42
 */
public interface LogDao {
    @Insert("INSERT INTO sys_log (visit_time, ip, url, execution_time, method) VALUES (#{log.visitTime}, #{log.ip}, #{log.url}, #{log.executionTime}, #{log.method})")
    Integer insert(@Param("log") SysLog sysLog);
}
