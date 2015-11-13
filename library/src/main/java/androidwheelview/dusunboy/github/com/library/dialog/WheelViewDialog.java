package androidwheelview.dusunboy.github.com.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.custom_view.DatePickerWheelView;
import androidwheelview.dusunboy.github.com.library.custom_view.LevelLinkageWheelView;
import androidwheelview.dusunboy.github.com.library.custom_view.NoLevelLinkageWheelView;
import androidwheelview.dusunboy.github.com.library.model.AreaBean;
import androidwheelview.dusunboy.github.com.library.util.DensityUtil;


/**
 * Created by Win8 on 2015/11/3.
 */
public class WheelViewDialog extends Dialog implements View.OnClickListener {

    /**
     * 日期模式
     */
    public static final int DATE = 0;
    /**
     * 日期加时间模式
     */
    public static final int DATE_TIME = 1;
    /**
     * 二级联动
     */
    public static final int TWO_LINKAGE = 3;
    /**
     * 三级联动
     */
    public static final int THREE_LINKAGE = 4;
    /**
     * 一级选择
     */
    public static final int ONE_LEVEL = 5;
    /**
     * 二级选择
     */
    public static final int TWO_LEVEL = 6;
    /**
     * 三级选择
     */
    public static final int THREE_LEVEL = 7;
    /**
     * 四级选择
     */
    public static final int FOUR_LEVEL = 8;
    /**
     * 五级选择
     */
    public static final int FIVE_LEVEL = 9;

    private final Context context;
    private final int mode;
    private TextView tv_title;
    private LinearLayout li_wheel_view;
    private DatePickerWheelView datePickerWheelView;
    private LinearLayout li_child;
    private CharSequence title;
    private String currentDate;
    private TextView tv_confirm;
    private TextView tv_cancel;
    private LinearLayout li_bottom;
    private boolean isPositiveButton;
    private String positiveButtonText;
    private boolean isNegativeButton;
    private String negativeButtonText;
    private OnSetListener onPositiveButtonClickListener;
    private OnSetListener onNegativeButtonClickListener;
    private LevelLinkageWheelView levelLinkageWheelView;
    private ArrayList<AreaBean> areaBeans;
    private ArrayList<String>[] arrayLists;
    private NoLevelLinkageWheelView noLevelLinkageWheelView;

    public WheelViewDialog(Context context, int mode) {
        super(context, R.style.DialogStyle);
        this.context = context;
        this.mode = mode;
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
        li_bottom = (LinearLayout) findViewById(R.id.li_bottom);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);


        init();
    }

    private void init() {
        if (mode == DATE || mode == DATE_TIME) {
            datePickerWheelView = new DatePickerWheelView(context, mode);
            datePickerWheelView.setDate(currentDate);
        } else if(mode == TWO_LINKAGE || mode == THREE_LINKAGE) {
            levelLinkageWheelView = new LevelLinkageWheelView(context, mode);
            levelLinkageWheelView.setData(areaBeans);
        } else if(mode == ONE_LEVEL || mode == TWO_LEVEL
                || mode == THREE_LEVEL || mode == FOUR_LEVEL
                || mode == FIVE_LEVEL) {
            noLevelLinkageWheelView = new NoLevelLinkageWheelView(context, mode);
            noLevelLinkageWheelView.setData(arrayLists);
        }

        LinearLayout.LayoutParams li_childLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        li_childLayoutParams.setMargins(DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30),
                DensityUtil.dp2px(context, 30), DensityUtil.dp2px(context, 30));
        li_child.setLayoutParams(li_childLayoutParams);
        li_child.setBackgroundResource(R.drawable.dialog_bg);

        RelativeLayout.LayoutParams li_bottomLayoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        li_bottomLayoutParams.topMargin = DensityUtil.dp2px(context, 20);
        li_bottom.setLayoutParams(li_bottomLayoutParams);
        li_bottom.setGravity(Gravity.RIGHT);

        LinearLayout.LayoutParams tv_cancelLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, 60),
                DensityUtil.dp2px(context, 50));
        tv_cancel.setGravity(Gravity.CENTER);
        tv_cancel.setLayoutParams(tv_cancelLayoutParams);
        tv_cancel.setTextSize(DensityUtil.sp2px(context, 8));
        if (isNegativeButton) {
            tv_cancel.setText(negativeButtonText);
            tv_cancel.setVisibility(View.VISIBLE);
        }

        LinearLayout.LayoutParams tv_confirmLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, 60),
                DensityUtil.dp2px(context, 50));
        tv_confirm.setGravity(Gravity.CENTER);
        tv_confirmLayoutParams.rightMargin = DensityUtil.dp2px(context, 15);
        tv_confirm.setLayoutParams(tv_confirmLayoutParams);
        tv_confirm.setTextSize(DensityUtil.sp2px(context, 8));
        if (isPositiveButton) {
            tv_confirm.setText(positiveButtonText);
            tv_confirm.setVisibility(View.VISIBLE);
        }

        if (mode == DATE || mode == DATE_TIME) {
            li_wheel_view.addView(datePickerWheelView);
        } else if(mode == TWO_LINKAGE || mode == THREE_LINKAGE) {
            li_wheel_view.addView(levelLinkageWheelView);
        }  else if(mode == ONE_LEVEL || mode == TWO_LEVEL
                || mode == THREE_LEVEL || mode == FOUR_LEVEL
                || mode == FIVE_LEVEL) {
            li_wheel_view.addView(noLevelLinkageWheelView);
        }

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
            if (onPositiveButtonClickListener != null) {
                if (mode == DATE || mode == DATE_TIME) {
                    OnDateSetListener onPositiveButtonClickListener = (OnDateSetListener) this.onPositiveButtonClickListener;
                    onPositiveButtonClickListener.onDateSet(this, datePickerWheelView.getCurrentYear(),
                            datePickerWheelView.getCurrentMonth() + 1, datePickerWheelView.getCurrentDay(),
                            datePickerWheelView.getCurrentHour(), datePickerWheelView.getCurrentMinutes());
                } else if(mode == TWO_LINKAGE || mode == THREE_LINKAGE) {
                    OnTextSetListener onPositiveButtonClickListener = (OnTextSetListener) this.onPositiveButtonClickListener;
                    onPositiveButtonClickListener.onTextSet(this, levelLinkageWheelView.getCurrentTextStr());
                } else if(mode == ONE_LEVEL || mode == TWO_LEVEL
                        || mode == THREE_LEVEL || mode == FOUR_LEVEL
                        || mode == FIVE_LEVEL) {
                    OnTextSetListener onPositiveButtonClickListener = (OnTextSetListener) this.onPositiveButtonClickListener;
                    onPositiveButtonClickListener.onTextSet(this, noLevelLinkageWheelView.getCurrentTextStr());
                }
            } else {
                dismiss();
            }
        } else if (v.getId() ==  R.id.tv_cancel) {
            if (onNegativeButtonClickListener != null) {
                if (mode == DATE || mode == DATE_TIME) {
                    OnDateSetListener onNegativeButtonClickListener = (OnDateSetListener) this.onNegativeButtonClickListener;
                    onNegativeButtonClickListener.onDateSet(this, datePickerWheelView.getCurrentYear(),
                            datePickerWheelView.getCurrentMonth() + 1, datePickerWheelView.getCurrentDay(),
                            datePickerWheelView.getCurrentHour(), datePickerWheelView.getCurrentMinutes());
                } else if(mode == TWO_LINKAGE || mode == THREE_LINKAGE) {
                    OnTextSetListener onNegativeButtonClickListener = (OnTextSetListener) this.onNegativeButtonClickListener;
                    onNegativeButtonClickListener.onTextSet(this, levelLinkageWheelView.getCurrentTextStr());
                } else if(mode == ONE_LEVEL || mode == TWO_LEVEL
                        || mode == THREE_LEVEL || mode == FOUR_LEVEL
                        || mode == FIVE_LEVEL) {
                    OnTextSetListener onNegativeButtonClickListener = (OnTextSetListener) this.onNegativeButtonClickListener;
                    onNegativeButtonClickListener.onTextSet(this, noLevelLinkageWheelView.getCurrentTextStr());
                }
            } else {
                dismiss();
            }
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
     * 设置日期
     * @param date
     *              1.模式为Date 格式2015-11-11
     *              2.模式为Date_Time 格式2015-11-11 05:11
     */
    public void setCurrentDate(String date) {
        currentDate = date;
    }

    public void setPositiveButton(String text, OnSetListener onPositiveButtonClickListener) {
        isPositiveButton = true;
        positiveButtonText = text;
        this.onPositiveButtonClickListener = onPositiveButtonClickListener;
    }

    public void setNegativeButton(String text, OnSetListener onNegativeButtonClickListener) {
        isNegativeButton = true;
        negativeButtonText = text;
        this.onNegativeButtonClickListener = onNegativeButtonClickListener;
    }


    public void setData(ArrayList<AreaBean> areaBeans) {
        this.areaBeans = areaBeans;
    }

    public void setData(ArrayList<String>... arrayLists) {
        this.arrayLists = arrayLists;
    }
}
