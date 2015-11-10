package androidwheelview.dusunboy.github.com.library.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.adapters.CalendarTextAdapter;
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
    private ArrayList<String> arry_years;
    private ArrayList<String> arry_months;
    private ArrayList<String> arry_days;
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


        arry_years = new ArrayList<String>();
        arry_months = new ArrayList<String>();
        arry_days = new ArrayList<String>();

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
     * 设置年月日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day) {

//        String startTime = DateUtil.second2TimeFormat("yyyy-MM-dd", String.valueOf(System.currentTimeMillis() / 1000));
//        String endTime = DateUtil.second2TimeFormat("yyyy-MM-dd", String.valueOf((System.currentTimeMillis() / 1000) + 6 * 30 * 24 * 3600));

        
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel.getId() == R.id.wv_first) {
            String currentText = (String) firstAdapter.get(wheel.getCurrentItem());
//            selectYear = currentText;
            setTextViewSize(currentText, firstAdapter);
            currentYear = Integer.parseInt(currentText);
//            setYear(currentYear);
//            initMonths(month);
            secondAdapter = new CalendarTextAdapter(context, arry_months, 0, maxTextSize, minTextSize);
            wv_second.setVisibleItems(5);
            wv_second.setViewAdapter(secondAdapter);
            wv_second.setCurrentItem(0);
        } else if (wheel.getId() == R.id.wv_second) {
            String currentText = (String) secondAdapter.get(wheel.getCurrentItem());
//            selectMonth = currentText;
            setTextViewSize(currentText, secondAdapter);
//            setMonth(Integer.parseInt(currentText));
//            initDays(day);
            thirdAdapter = new CalendarTextAdapter(context, arry_days, 0, maxTextSize, minTextSize);
            wv_third.setVisibleItems(5);
            wv_third.setViewAdapter(thirdAdapter);
            wv_third.setCurrentItem(0);
        } else if (wheel.getId() == R.id.wv_third) {
            String currentText = (String) thirdAdapter.get(wheel.getCurrentItem());
            setTextViewSize(currentText, thirdAdapter);
//            selectDay = currentText;
        }
    }

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        if (wheel.getId() == R.id.wv_first) {
            String currentText = (String) firstAdapter.get(wheel.getCurrentItem());
            setTextViewSize(currentText, firstAdapter);
        } else if (wheel.getId() == R.id.wv_second) {
            String currentText = (String) secondAdapter.get(wheel.getCurrentItem());
            setTextViewSize(currentText, secondAdapter);
        } else if (wheel.getId() == R.id.wv_third) {
            String currentText = (String) thirdAdapter.get(wheel.getCurrentItem());
            setTextViewSize(currentText, thirdAdapter);
        }
    }



    /**
     * 设置字体大小
     * @param currentItemText
     * @param adapter
     */
    public void setTextViewSize(String currentItemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textView = (TextView) arrayList.get(i);
            currentText = textView.getText().toString();
            if (currentItemText.equals(currentText)) {
                textView.setTextSize(maxTextSize);
            } else {
                textView.setTextSize(minTextSize);
            }
        }
    }

}
