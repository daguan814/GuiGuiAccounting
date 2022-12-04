/*
 * @Author      : daguan814
 * @date        : 2022/9/10 16:57
 * @Description :
 */


package org.shuijing.guiguijizhang.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 公共字段填充
 */
@Component //让spring管理它
@Slf4j  //日志
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {  //创建信息

        metaObject.setValue("id", UUID.randomUUID());

    }

    @Override
    public void updateFill(MetaObject metaObject) { //修改信息
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());

    }
}
