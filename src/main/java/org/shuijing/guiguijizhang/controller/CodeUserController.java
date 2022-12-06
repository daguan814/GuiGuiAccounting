package org.shuijing.guiguijizhang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;



import lombok.extern.slf4j.Slf4j;
import org.shuijing.guiguijizhang.common.Result;
import org.shuijing.guiguijizhang.pojo.CodeUser;
import org.shuijing.guiguijizhang.service.CodeUserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 激活界面
 * </p>
 *
 * @author baomidou
 * @since 2022-12-01
 */
@RestController
@RequestMapping("/codeUser")
@Slf4j
public class CodeUserController {

    @Autowired
    private CodeUserService codeUserService;


    /**
     * 登陆时查看激活
     *
     * @param codeUser
     * @return
     */

    @Cacheable(value = "userCache", key = "#codeUser.iemi")
    @PostMapping("/login")
    public Result<CodeUser> login(@RequestBody CodeUser codeUser) {
        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<CodeUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeUser::getIemi, codeUser.getIemi());
        CodeUser cu = codeUserService.getOne(queryWrapper);
        //如果没有查到返回登录失败
        if (cu == null) {
            return Result.error("没有激活，登录失败！");
        }

        return Result.success(codeUser);
    }

    /**
     * 后台新增一个机器码和激活码绑定
     *
     * @param codeUser
     * @return CachePut：将方法返回值放入缓存
     * value：缓存的名称，每个名称下可以有多个key
     * key：缓存的key
     */
    @CachePut(value = "userCache", key = "#codeUser.iemi") //将方法返回值放入缓存
    @PostMapping("/add")
    public Result<CodeUser> add(@RequestBody CodeUser codeUser) {
        codeUserService.save(codeUser);
        return Result.success(codeUser);
    }

    /**
     * 新机器激活
     *
     * @param codeUser
     * @return
     */


    @CachePut(value = "userCache", key = "#codeUser.iemi") //将方法返回值放入缓存
    @PostMapping("/jihuo")
    public Result<CodeUser> jihuo(@RequestBody CodeUser codeUser) {

        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<CodeUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeUser::getPwdcode, codeUser.getPwdcode());
        CodeUser cu = codeUserService.getOne(queryWrapper);

        //如果没有查到返回登录失败
        if (cu == null) {
            return Result.error("没有该激活码！");
        }
        if (cu.getIemi() != null && !cu.getIemi().equals("0")) {
            return Result.error("该激活码已被使用！");
        }
        //验证码查到，且没被使用，将机器码加入数据库
        codeUserService.updateById(codeUser);
        return Result.success(codeUser);
    }

    /**
     * 释放激活码
     *
     * @param codeUser
     * @return
     */


    @CacheEvict(value = "userCache", key = "#codeUser.iemi")
    @PostMapping("/del")
    public Result<String> del(@RequestBody CodeUser codeUser) {

        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<CodeUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeUser::getIemi, codeUser.getIemi());
        CodeUser cu = codeUserService.getOne(queryWrapper);

        if (cu == null) {
            return Result.error("本机没有激活码！非法请求");
        }
        //退出后，把iemi变成0
        cu.setIemi("0");

        codeUserService.updateById(cu);
        return Result.success("已成功退出！");
    }


}
