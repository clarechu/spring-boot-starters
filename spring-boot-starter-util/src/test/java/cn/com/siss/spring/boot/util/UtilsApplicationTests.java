package cn.com.siss.spring.boot.util;

import cn.com.siss.spring.boot.util.base.BackResponseUtil;
import cn.com.siss.spring.boot.util.base.BaseResponse;
import cn.com.siss.spring.boot.util.base.MessageCodeConstant;
import cn.com.siss.spring.boot.util.base.ReturnCodeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void baseGetName() {
        String message = ReturnCodeEnum.getName(1001);
        Assert.assertEquals(message, MessageCodeConstant.MESSAGE_SECURITY_UNAUTHORIZED);
        message = ReturnCodeEnum.getName(0);
        Assert.assertEquals(message,null);
    }

    @Test
    public void getBaseResponse() {
        BaseResponse baseResponse = BackResponseUtil.setBaseResponse(ReturnCodeEnum.CODE_1001.getCode());
        BaseResponse baseResponse1 = new BaseResponse();
        baseResponse1.setCode(1001);
        baseResponse1.setMessage(MessageCodeConstant.MESSAGE_SECURITY_UNAUTHORIZED);
        Assert.assertEquals(baseResponse, baseResponse1);
    }
}
