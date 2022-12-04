/*
 * @Author      : daguan814
 * @date        : 2022/9/11 10:47
 * @Description :
 */


package org.shuijing.guiguijizhang.common;


/**
 * 基于ThreadLocal封装的工具类，用户保存和获取登陆用户Id
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) { //设置为静态方法，可以直接用类名调用
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }


}
