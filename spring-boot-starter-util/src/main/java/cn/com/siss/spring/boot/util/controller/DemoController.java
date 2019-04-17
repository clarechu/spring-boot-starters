package cn.com.siss.spring.boot.util.controller;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import cn.com.siss.spring.boot.util.date.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author clare
 * @Date 2019/4/3 15:38
 * @Version 1.0
 */


@RestController
public class DemoController {

    @PostMapping("get")
    public BaseResponse get(@RequestBody User object){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_COMMON_SUCCESS.getCode());
        baseResponse.setData(object);
        System.out.println("get 1");
        return baseResponse;
    }

    @GetMapping("get1")
    public void get1(){
        System.out.println("get 2");
    }

}

