package cn.com.siss.spring.boot.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 时间
 * @Author clare
 * @Date 2019/2/19 15:18
 * @Version 1.0
 */
public class DateUtil {
    public static String FormatDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = sdf.format(date);
        return da;
    }

    public static String getNowDate(Long date){
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = time.format(date);
        return formatDate;
    }
}
