package androidwheelview.dusunboy.github.com.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.custom_view.DatePickerWheelView;
import androidwheelview.dusunboy.github.com.library.util.DensityUtil;


/**
 * Created by Win8 on 2015/11/3.
 */
public class DateWheelViewDialog extends Dialog implements View.OnClickListener {

    private final Context context;
    private TextView tv_title;
    private LinearLayout li_wheel_view;
    private DatePickerWheelView datePickerWheelView;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private LinearLayout li_child;
    private CharSequence title;

    public DateWheelViewDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_wheel);

        tv_title = (TextView) findViewById(R.id.tv_title);

        li_wheel_view = (LinearLayout) findViewById(R.id.li_wheel_view);
        LinearLayout li = (LinearLayout) findViewById(R.id.li);
        li.setOnClickListener(this);
        li_child = (LinearLayout) findViewById(R.id.li_child);
        li_child.setOnClickListener(this);
        TextView tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);


        init();
    }

    private void init() {
        datePickerWheelView = new DatePickerWheelView(context, DatePickerWheelView.DATE);
        datePickerWheelView.setDate(currentYear, currentMonth, currentDay);

        LinearLayout.LayoutParams li_childLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        li_childLayoutParams.setMargins(DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30),
                DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30));
        li_child.setLayoutParams(li_childLayoutParams);
        li_child.setBackgroundResource(R.drawable.dialog_bg);


        li_wheel_view.addView(datePickerWheelView);

        LinearLayout.LayoutParams tv_titleLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        tv_title.setTextColor(context.getResources().getColor(android.R.color.black));
        tv_title.setTextSize(DensityUtil.sp2px(context, 12));
        tv_title.setPadding(DensityUtil.dp2px(context, 18), DensityUtil.dp2px(context, 18),
                DensityUtil.dp2px(context, 18), DensityUtil.dp2px(context, 18));
        tv_title.setLayoutParams(tv_titleLayoutParams);
        tv_title.setText(title);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() ==  R.id.tv_confirm) {
        } else if (v.getId() ==  R.id.tv_cancel) {
            dismiss();
        } else if (v.getId() ==  R.id.li_child) {
        } else if (v.getId() ==  R.id.li) {
            dismiss();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
//        super.setTitle(title);
        this.title = title;

    }

    /**
     * 设置当前日期
     * @param year
     * @param month
     * @param day
     */
    public void setCurrentDate(int year, int month, int day) {
        currentYear = year;
        currentMonth = month;
        currentDay  = day;
    }
}
