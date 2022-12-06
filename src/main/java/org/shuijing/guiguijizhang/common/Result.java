package org.shuijing.guiguijizhang.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Result
 * @Description: TODO
 * @author: 达观
 * @date: 2022/7/18  16:46
 */

/**
 * 通用返回结果
 */

@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    private Map map = new HashMap(); //动态数据

    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.data = object;
        result.code  = 200;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result result = new Result();
        result.msg = msg;
        result.code  = 100;
        return result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key,value);
        return this;
    }


}
