package org.shuijing.guiguijizhang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import lombok.extern.slf4j.Slf4j;
import org.shuijing.guiguijizhang.common.Result;
import org.shuijing.guiguijizhang.pojo.CodeUser;
import org.shuijing.guiguijizhang.service.CodeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 前端控制器
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

    @PostMapping("/login")
    public Result<String> login(@RequestBody CodeUser codeUser) {


        //根据页面提交的用户名查询数据库
        LambdaQueryWrapper<CodeUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CodeUser::getIemi, codeUser.getIemi());
        CodeUser cu = codeUserService.getOne(queryWrapper);

        //如果没有查到返回登录失败
        if (cu == null) {
            return Result.error("没有激活，登录失败！");
        }


        return Result.success("","");
    }

    @PostMapping("/jihuo")
    public Result<String> jihuo( @RequestBody CodeUser codeUser) {

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
        return Result.success("激活成功","");
    }

    @PostMapping("/del")
    public Result<String> del( @RequestBody CodeUser codeUser) {

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
        return Result.success("释放激活码成功，您下次使用本软件时将要求您激活","");
    }


}
