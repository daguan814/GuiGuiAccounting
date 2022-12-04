package org.shuijing.guiguijizhang.common;

/**
 * @ClassName: GlobalExceptionCatch
 * @Description: TODO
 * @author: 达观
 * @date: 2022/7/30  10:36
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionCatch {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) //完整性约束的异常
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")){  //判断是不是重复的
            String[] split = ex.getMessage().split(" ");//以空格作为分割整个异常语句
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }

        return Result.error("未知错误！");
    }


    @ExceptionHandler(CustomException.class) //
    public Result<String> exceptionHandler(CustomException ex){

        return Result.error(ex.getMessage());
    }
}
