package androidwheelview.dusunboy.github.com.library.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.adapters.CalendarTextAdapter;
import androidwheelview.dusunboy.github.com.library.util.DateUtil;
import androidwheelview.dusunboy.github.com.library.util.DensityUtil;
import androidwheelview.dusunboy.github.com.library.views.OnWheelChangedListener;
import androidwheelview.dusunboy.github.com.library.views.OnWheelScrollListener;
import androidwheelview.dusunboy.github.com.library.views.WheelView;

/**
 * Created by Win8 on 2015/11/9.
 */
public class DatePickerWheelView extends LinearLayout implements OnWheelChangedListener, OnWheelScrollListener {

    /**
     * 日期模式
     */
    public static final int DATE = 0;
    /**
     * 日期加时间模式
     */
    public static final int DATE_TIME = 1;
    private  Context context;
    private  WheelView wv_first;
    private  WheelView wv_second;
    private  WheelView wv_third;
    private ArrayList<String> firstArray;
    private ArrayList<String> secondArray;
    private ArrayList<String> thirdArray;
    private CalendarTextAdapter firstAdapter;
    private CalendarTextAdapter secondAdapter;
    private CalendarTextAdapter thirdAdapter;

    private int currentYear;
    private int currentMonth;
    private int currentDay;

    private int month;
    private int day;
    private LinearLayout li;
    private View view;
    private int viewWidth;
    private int mode;
    private WheelView wv_fourth;
    private WheelView wv_fifth;
    private TextView tv_year;
    private TextView tv_month;
    private TextView tv_day;
    private TextView tv_hour;
    private TextView tv_minute;
    private int maxTextSize;
    private int minTextSize;
    private int visibleItems;
    private ArrayList<String> fourthArray;
    private ArrayList<String> fifthArray;
    private int beforeYearRange;
    private int afterYearRange;
    private int beforeYear;
    private int afterYear;
    private ArrayList<String> monthArray;
    private ArrayList<String> currentYearRanges;

    public DatePickerWheelView(Context context, int mode) {
        super(context);
        this.context = context;
        this.mode = mode;

        initView();
        init();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.view_date_picker_wheel, this);

        li = (LinearLayout) findViewById(R.id.li);
        wv_first = (WheelView) findViewById(R.id.wv_first);
        wv_second = (WheelView) findViewById(R.id.wv_second);
        wv_third = (WheelView) findViewById(R.id.wv_third);
        wv_fourth = (WheelView) findViewById(R.id.wv_fourth);
        wv_fifth = (WheelView) findViewById(R.id.wv_fifth);

        tv_year = (TextView) findViewById(R.id.tv_year);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_hour = (TextView) findViewById(R.id.tv_hour);
        tv_minute = (TextView) findViewById(R.id.tv_minute);

        if (mode == DATE) {
            wv_fourth.setVisibility(GONE);
            wv_fourth.setVisibility(GONE);
            tv_hour.setVisibility(GONE);
            tv_minute.setVisibility(GONE);
        } else if (mode == DATE_TIME) {
            wv_fourth.setVisibility(VISIBLE);
            wv_fifth.setVisibility(VISIBLE);
            tv_hour.setVisibility(VISIBLE);
            tv_minute.setVisibility(VISIBLE);
        }
    }

    private void init() {
        viewWidth = 280;
        maxTextSize = 24;
        minTextSize = 14;
        visibleItems = 5;
        beforeYearRange = 20;
        afterYearRange = 5;

        LinearLayout.LayoutParams liLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, viewWidth),
                DensityUtil.dp2px(context, 160));
        liLayoutParams.setMargins(DensityUtil.dp2px(context, 10), 0, DensityUtil.dp2px(context, 10), 0);
        li.setLayoutParams(liLayoutParams);


        if (mode == DATE) {
            LinearLayout.LayoutParams wheelViewLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, (viewWidth - 50) / 3),
                    LinearLayout.LayoutParams.MATCH_PARENT);
            wv_first.setLayoutParams(wheelViewLayoutParams);
            wv_second.setLayoutParams(wheelViewLayoutParams);
            wv_third.setLayoutParams(wheelViewLayoutParams);
        } else if (mode == DATE_TIME) {
            LinearLayout.LayoutParams wheelViewLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, viewWidth / 8 * 2),
                    LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams wheelViewLayoutParams2 = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, viewWidth / 8),
                    LinearLayout.LayoutParams.MATCH_PARENT);
            wv_first.setLayoutParams(wheelViewLayoutParams);
            wv_second.setLayoutParams(wheelViewLayoutParams2);
            wv_third.setLayoutParams(wheelViewLayoutParams2);
            wv_fourth.setLayoutParams(wheelViewLayoutParams2);
            wv_fifth.setLayoutParams(wheelViewLayoutParams2);
        }


        firstArray = new ArrayList<String>();
        secondArray = new ArrayList<String>();
        thirdArray = new ArrayList<String>();
        fourthArray = new ArrayList<String>();
        fifthArray = new ArrayList<String>();

        monthArray = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            monthArray.add(String.valueOf(i + 1));
        }


        firstAdapter = new CalendarTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_first.setVisibleItems(visibleItems);
        wv_first.setViewAdapter(firstAdapter);
        wv_first.setCurrentItem(0);
        wv_first.addChangingListener(this);
        wv_first.addScrollingListener(this);

        secondAdapter = new CalendarTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_second.setVisibleItems(visibleItems);
        wv_second.setViewAdapter(secondAdapter);
        wv_second.setCurrentItem(0);
        wv_second.addChangingListener(this);
        wv_second.addScrollingListener(this);

        thirdAdapter = new CalendarTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_third.setVisibleItems(visibleItems);
        wv_third.setViewAdapter(thirdAdapter);
        wv_third.setCurrentItem(0);
        wv_third.addChangingListener(this);
        wv_third.addScrollingListener(this);

    }

    /**
     * 设置日期
     * @param currentDate
     */
    public void setDate(String currentDate) {
        Date currentDates = DateUtil.timeFormat2Date("yyyy-MM-dd", currentDate);
        currentYear = currentDates.getYear() + 1900;
        currentMonth = currentDates.getMonth();
        currentDay = currentDates.getDate();
        beforeYear = currentYear - beforeYearRange;
        afterYear = currentYear + afterYearRange;
        firstAdapter.clear();
        secondAdapter.clear();
        thirdAdapter.clear();

        //年
        currentYearRanges = getYearRange(beforeYear, afterYear);
        firstAdapter.addAll(currentYearRanges);
        //月份
        secondAdapter.addAll(monthArray);
        //日
        int maxDaysOfMonth = DateUtil.getMaxDaysOfMonth(currentYear, currentMonth);
        for (int i = 1; i <= maxDaysOfMonth; i++) {
            thirdAdapter.add(String.valueOf(i));
        }

        firstAdapter.notifyDataChangedEvent();
        secondAdapter.notifyDataChangedEvent();
        thirdAdapter.notifyDataChangedEvent();
        wv_first.setCurrentItem(beforeYearRange);
        wv_second.setCurrentItem(currentMonth);
        wv_third.setCurrentItem(currentDay - 1);
    }

    /**
     * 获取年份范围
     * @param beforeYear
     * @param afterYear
     */
    private ArrayList<String> getYearRange(int beforeYear, int afterYear) {
        ArrayList<String> array = new ArrayList<String>();
        for (int i = beforeYear; i <= afterYear; i++) {
            array.add(String.valueOf(i));
        }
        return array;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel.getId() == R.id.wv_first) {
            currentYear = Integer.parseInt(firstAdapter.get(wheel.getCurrentItem()));
            firstAdapter.setCurrentIndex(wheel.getCurrentItem());
            firstAdapter.notifyDataChangedEvent();

            resetDayWheelView();
        } else if (wheel.getId() == R.id.wv_second) {
            currentMonth = Integer.parseInt(secondAdapter.get(wheel.getCurrentItem())) - 1;
            secondAdapter.setCurrentIndex(wheel.getCurrentItem());

            resetDayWheelView();
            secondAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_third) {
            currentDay = Integer.parseInt(thirdAdapter.get(wheel.getCurrentItem()));
            thirdAdapter.setCurrentIndex(wheel.getCurrentItem());
            thirdAdapter.notifyDataChangedEvent();
        }
    }

    /**
     * 重置天的滚轮
     */
    private void resetDayWheelView() {
        thirdAdapter.clear();
        int maxDaysOfMonth = DateUtil.getMaxDaysOfMonth(currentYear, currentMonth);
        for (int i = 1; i <= maxDaysOfMonth; i++) {
            thirdAdapter.add(String.valueOf(i));
        }
        wv_third.setCurrentItem(0);
        wv_third.setViewAdapter(thirdAdapter);
        thirdAdapter.notifyDataChangedEvent();
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        if (wheel.getId() == R.id.wv_first) {
            firstAdapter.setCurrentIndex(wheel.getCurrentItem());
            wv_first.setCurrentItem(wheel.getCurrentItem());
            firstAdapter.notifyDataChangedEvent();

        } else if (wheel.getId() == R.id.wv_second) {
            secondAdapter.setCurrentIndex(wheel.getCurrentItem());
            wv_second.setCurrentItem(wheel.getCurrentItem());
            secondAdapter.notifyDataChangedEvent();

        } else if (wheel.getId() == R.id.wv_third) {
            thirdAdapter.setCurrentIndex(wheel.getCurrentItem());
            wv_third.setCurrentItem(wheel.getCurrentItem());
            thirdAdapter.notifyDataChangedEvent();
        }
    }
}
