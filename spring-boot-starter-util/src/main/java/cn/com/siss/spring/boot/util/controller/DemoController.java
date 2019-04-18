package cn.com.siss.spring.boot.util.controller;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import cn.com.siss.spring.boot.util.config.HttpClientConfigure;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author clare
 * @Date 2019/4/3 15:38
 * @Version 1.0
 */


@RestController
public class DemoController {

    @Autowired
    private CloseableHttpClient httpClient;

    @PostMapping("get")
    public BaseResponse get(@RequestBody Object object){
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.MESSAGE_COMMON_SUCCESS.getCode());
        baseResponse.setData(object);
        System.out.println("get 1");
        return baseResponse;
    }

    @GetMapping("get1")
    public void get1(){
        System.out.println("get 2");
    }

    @GetMapping("get2")
    public void get2() throws IOException, ClientProtocolException {
        HttpGet httpGet = new HttpGet("");
        httpClient.execute(httpGet);
        System.out.println("get 2");
    }
}

