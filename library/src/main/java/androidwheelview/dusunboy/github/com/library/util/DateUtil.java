package androidwheelview.dusunboy.github.com.library.util;


import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidwheelview.dusunboy.github.com.library.R;

/**
 * Created by Win8 on 2015/2/9.
 */
public class DateUtil {


    /**
     * 秒转为时间格式<br/>
     * yyyy-MM-dd HH:mm<br/>
     * MM月dd日 EEE HH:mm<br/>
     * @param timeFormat
     * @param time
     * @return
     */
    public static String second2TimeFormat(String timeFormat, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        Date date = new Date(Long.valueOf(time) * 1000);
        return simpleDateFormat.format(date);
    }


    /**
     * 日期格式转为秒
     * @param timeFormat
     * @param time
     * @return
     */
    public static long timeFormat2Second(String timeFormat, String time) {
        Date date = timeFormat2Date(timeFormat, time);
        if (date == null) {
            return 0;
        } else {
            return ((date.getTime()) / 1000);
        }
    }

    /**
     * 日期格式转为Date
     * @param timeFormat
     * @param time
     * @return
     */
    public static Date timeFormat2Date(String timeFormat, String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        try {
            Date date = formatter.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 选择时间段
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<Date> selectTimePeriod(String startTime, String endTime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = null;
        try {
            dBegin = sdf.parse(startTime);
            Date dEnd = sdf.parse(endTime);
            List<Date> lDate = getDatesPeriod(dBegin, dEnd);
            return lDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取日期段
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> getDatesPeriod(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 计算日期对应的星期
     * @param pTime
     * @return
     */
    public static String getWeek(Context context, String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String week = "";
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            week += context.getString(R.string.sunday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            week += context.getString(R.string.monday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            week += context.getString(R.string.tuesday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            week += context.getString(R.string.wednesday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            week += context.getString(R.string.thursday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            week += context.getString(R.string.friday);
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            week += context.getString(R.string.saturday);
        }
        return week;
    }

    /**
     * 获取某年某月最大天数
     * @param currentYear
     * @param currentMonth
     */
    public static int getMaxDaysOfMonth(int currentYear, int currentMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(currentYear, currentMonth, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
