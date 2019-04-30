package cn.com.siss.spring.boot.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    /**
     * @return
     */
    public static Long getZeroTimeOfToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    public static Long getLastTimeOfToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime();
    }

}
