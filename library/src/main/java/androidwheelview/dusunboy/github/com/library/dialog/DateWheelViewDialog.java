package androidwheelview.dusunboy.github.com.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
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
    private LinearLayout li_child;
    private CharSequence title;
    private String currentDate;
    private TextView tv_confirm;
    private TextView tv_cancel;
    private LinearLayout li_bottom;
    private OnClickListener onPositiveButtonClickListener;

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
        li_bottom = (LinearLayout) findViewById(R.id.li_child);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);


        init();
    }

    private void init() {
        datePickerWheelView = new DatePickerWheelView(context, DatePickerWheelView.DATE);
        datePickerWheelView.setDate(currentDate);

        LinearLayout.LayoutParams li_childLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        li_childLayoutParams.setMargins(DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30),
                DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30));
        li_child.setLayoutParams(li_childLayoutParams);
        li_child.setBackgroundResource(R.drawable.dialog_bg);

        LinearLayout.LayoutParams li_bottomLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        li_bottomLayoutParams.bottomMargin = DensityUtil.dp2px(context, 21);
        li_bottomLayoutParams.topMargin =  DensityUtil.dp2px(context, 25);
        li_bottomLayoutParams.gravity = Gravity.RIGHT;
        li_bottom.setLayoutParams(li_bottomLayoutParams);

        LinearLayout.LayoutParams tv_cancelLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, 77),
                DensityUtil.dp2px(context, 33));
        tv_cancelLayoutParams.gravity = Gravity.CENTER;
        tv_cancel.setLayoutParams(tv_cancelLayoutParams);
        tv_cancel.setTextSize(DensityUtil.sp2px(context, 16));

        LinearLayout.LayoutParams tv_confirmLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, 77),
                DensityUtil.dp2px(context, 33));
        tv_confirmLayoutParams.gravity = Gravity.CENTER;
        tv_confirmLayoutParams.rightMargin = DensityUtil.dp2px(context, 15);
        tv_confirm.setLayoutParams(tv_confirmLayoutParams);
        tv_confirm.setTextSize(DensityUtil.sp2px(context, 16));

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
            onPositiveButtonClickListener.onClick(this, 0);
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
     * @param date 格式2015-11-11
     */
    public void setCurrentDate(String date) {
        currentDate = date;
    }

    public void setPositiveButton(String text, OnClickListener onPositiveButtonClickListener) {
        tv_confirm.setText(text);
        tv_confirm.setVisibility(View.VISIBLE);
        this.onPositiveButtonClickListener = onPositiveButtonClickListener;
    }
}
