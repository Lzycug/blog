package com.lzycug.aop.config;

import java.lang.annotation.*;

/**
 * description：自定义注解类，在需要记录操作的方法或者类上添加该注解
 * @Target({ElementType.METHOD,ElementType.TYPE}) 表示这个注解可以用用在类/接口上，还可以用在方法上
 * @Retention(RetentionPolicy.RUNTIME) 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息，而不像基本注解如@Override 那种不用运行，在编译时就可以进行相关工作的编译时注解。
 * @Inherited 表示这个注解可以被子类继承
 * @Documented 表示当执行javadoc的时候，本注解会生成相关文档
————————————————
版权声明：本文为CSDN博主「lizhi_ma」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_31279347/article/details/85106354
 * author：lzyCug
 * date: 2020/4/5 23:11
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogAop {
}
