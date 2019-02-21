package cn.com.siss.spring.boot.util;

import cn.com.siss.spring.boot.util.date.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName DateTest
 * @Description TODO
 * @Author clare
 * @Date 2019/2/19 15:20
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTest {

    @Test
    public void getDate() {
        String nowTime = DateUtil.getNowDate(System.currentTimeMillis());
        System.out.println(nowTime);
        nowTime = DateUtil.FormatDate();
        System.out.println(nowTime);
    }
}
