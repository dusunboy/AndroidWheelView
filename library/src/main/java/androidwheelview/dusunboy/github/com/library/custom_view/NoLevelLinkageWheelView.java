package androidwheelview.dusunboy.github.com.library.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.adapters.WheelTextAdapter;
import androidwheelview.dusunboy.github.com.library.dialog.WheelViewDialog;
import androidwheelview.dusunboy.github.com.library.util.DensityUtil;
import androidwheelview.dusunboy.github.com.library.views.OnWheelChangedListener;
import androidwheelview.dusunboy.github.com.library.views.OnWheelScrollListener;
import androidwheelview.dusunboy.github.com.library.views.WheelView;

/**
 * Created by Win8 on 2015/11/9.
 */
public class NoLevelLinkageWheelView extends LinearLayout implements OnWheelChangedListener, OnWheelScrollListener {

    private int level;
    private  Context context;
    private  WheelView wv_first;
    private  WheelView wv_second;
    private  WheelView wv_third;
    private WheelView wv_fourth;
    private WheelView wv_fifth;

    private WheelTextAdapter firstAdapter;
    private WheelTextAdapter secondAdapter;
    private WheelTextAdapter thirdAdapter;
    private WheelTextAdapter fourthAdapter;
    private WheelTextAdapter fifthAdapter;

    private int currentFirst;
    private int currentSecond;
    private int currentThird;
    private int currentFourth;
    private int currentFifth;


    private LinearLayout li;
    private View view;
    private int viewWidth;
    private int mode;

    private int maxTextSize;
    private int minTextSize;
    private int visibleItems;

    private ArrayList<String>[] arrayLists;

    public NoLevelLinkageWheelView(Context context, int mode) {
        super(context);
        this.context = context;
        this.mode = mode;
        if (mode == WheelViewDialog.ONE_LEVEL) {
            level = 1;
        } else if (mode == WheelViewDialog.TWO_LEVEL) {
            level = 2;
        } else if (mode == WheelViewDialog.THREE_LEVEL) {
            level = 3;
        } else if (mode == WheelViewDialog.FOUR_LEVEL) {
            level = 4;
        } else if (mode == WheelViewDialog.FIVE_LEVEL) {
            level = 5;
        }
        initView();
        init();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.view_level_linkage_wheel, this);

        li = (LinearLayout) findViewById(R.id.li);
        wv_first = (WheelView) findViewById(R.id.wv_first);
        wv_second = (WheelView) findViewById(R.id.wv_second);
        wv_third = (WheelView) findViewById(R.id.wv_third);
        wv_fourth = (WheelView) findViewById(R.id.wv_fourth);
        wv_fifth = (WheelView) findViewById(R.id.wv_fifth);

        for (int i = 0; i < li.getChildCount(); i++) {
            if(i < level) {
                li.getChildAt(i).setVisibility(VISIBLE);
            }
        }
    }

    private void init() {
        viewWidth = 280;
        if (level <= 3) {
            maxTextSize = 20;
            minTextSize = 12;
        } else  {
            maxTextSize = 14;
            minTextSize = 10;
        }
        visibleItems = 5;

        LayoutParams liLayoutParams = new LayoutParams(DensityUtil.dp2px(context, viewWidth),
                DensityUtil.dp2px(context, 160));
        liLayoutParams.setMargins(DensityUtil.dp2px(context, 10), 0, DensityUtil.dp2px(context, 10), 0);
        li.setLayoutParams(liLayoutParams);


        LinearLayout.LayoutParams wheelViewLayoutParams = new LinearLayout.LayoutParams(DensityUtil.dp2px(context, viewWidth / level),
                LinearLayout.LayoutParams.MATCH_PARENT);
        wv_first.setLayoutParams(wheelViewLayoutParams);
        wv_second.setLayoutParams(wheelViewLayoutParams);
        wv_third.setLayoutParams(wheelViewLayoutParams);
        wv_fourth.setLayoutParams(wheelViewLayoutParams);
        wv_fifth.setLayoutParams(wheelViewLayoutParams);

        firstAdapter = new WheelTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_first.setVisibleItems(visibleItems);
        wv_first.setViewAdapter(firstAdapter);
        wv_first.setCurrentItem(0);
        wv_first.addChangingListener(this);
        wv_first.addScrollingListener(this);

        secondAdapter = new WheelTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_second.setVisibleItems(visibleItems);
        wv_second.setViewAdapter(secondAdapter);
        wv_second.setCurrentItem(0);
        wv_second.addChangingListener(this);
        wv_second.addScrollingListener(this);

        thirdAdapter = new WheelTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_third.setVisibleItems(visibleItems);
        wv_third.setViewAdapter(thirdAdapter);
        wv_third.setCurrentItem(0);
        wv_third.addChangingListener(this);
        wv_third.addScrollingListener(this);

        fourthAdapter = new WheelTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_fourth.setVisibleItems(visibleItems);
        wv_fourth.setViewAdapter(fourthAdapter);
        wv_fourth.setCurrentItem(0);
        wv_fourth.addChangingListener(this);
        wv_fourth.addScrollingListener(this);

        fifthAdapter = new WheelTextAdapter(context, new ArrayList<String>(), 0, maxTextSize, minTextSize);
        wv_fifth.setVisibleItems(visibleItems);
        wv_fifth.setViewAdapter(fifthAdapter);
        wv_fifth.setCurrentItem(0);
        wv_fifth.addChangingListener(this);
        wv_fifth.addScrollingListener(this);

    }

    /**
     * 设置级联数据
     * @param arrayLists
     */
    public void setData(ArrayList<String>[] arrayLists) {

        currentFirst = 0;
        currentSecond = 0;
        currentThird = 0;
        currentFourth = 0;
        currentFifth = 0;
        this.arrayLists = arrayLists;
        resetLevelData();
    }

    /**
     * 获取各个级联的数据
     */
    private void resetLevelData() {
        if (arrayLists != null) {
            firstAdapter.clear();
            secondAdapter.clear();
            thirdAdapter.clear();
            fourthAdapter.clear();
            fifthAdapter.clear();

            for (int i = 0; i < arrayLists.length; i++) {
                if (i == 0) {
                    firstAdapter.addAll(arrayLists[0]);
                }
                if (i == 1) {
                    secondAdapter.addAll(arrayLists[1]);
                }
                if (i == 2) {
                    thirdAdapter.addAll(arrayLists[2]);
                }
                if (i == 3) {
                    fourthAdapter.addAll(arrayLists[3]);
                }
                if (i == 4) {
                    fifthAdapter.addAll(arrayLists[4]);
                }
            }

            wv_first.setViewAdapter(firstAdapter);
            wv_second.setViewAdapter(secondAdapter);
            wv_third.setViewAdapter(thirdAdapter);
            wv_fourth.setViewAdapter(fourthAdapter);
            wv_fifth.setViewAdapter(fifthAdapter);

            wv_first.setCurrentItem(currentFirst);
            wv_second.setCurrentItem(currentSecond);
            wv_third.setCurrentItem(currentThird);
            wv_fourth.setCurrentItem(currentFourth);
            wv_fifth.setCurrentItem(currentFifth);

            firstAdapter.notifyDataChangedEvent();
            secondAdapter.notifyDataChangedEvent();
            thirdAdapter.notifyDataChangedEvent();
            fourthAdapter.notifyDataChangedEvent();
            fifthAdapter.notifyDataChangedEvent();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel.getId() == R.id.wv_first) {
            currentFirst = wheel.getCurrentItem();
            firstAdapter.setCurrentIndex(wheel.getCurrentItem());
            firstAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_second) {
            currentSecond = wheel.getCurrentItem();
            secondAdapter.setCurrentIndex(wheel.getCurrentItem());
            secondAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_third) {
            currentThird = wheel.getCurrentItem();
            thirdAdapter.setCurrentIndex(wheel.getCurrentItem());
            thirdAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_fourth) {
            currentFourth = wheel.getCurrentItem();
            fourthAdapter.setCurrentIndex(wheel.getCurrentItem());
            fourthAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_fifth) {
            currentFifth = wheel.getCurrentItem();
            fifthAdapter.setCurrentIndex(wheel.getCurrentItem());
            fifthAdapter.notifyDataChangedEvent();
        }
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
        } else if (wheel.getId() == R.id.wv_fourth) {
            fourthAdapter.setCurrentIndex(wheel.getCurrentItem());
            wv_fourth.setCurrentItem(wheel.getCurrentItem());
            fourthAdapter.notifyDataChangedEvent();
        } else if (wheel.getId() == R.id.wv_fifth) {
            fifthAdapter.setCurrentIndex(wheel.getCurrentItem());
            wv_fifth.setCurrentItem(wheel.getCurrentItem());
            fifthAdapter.notifyDataChangedEvent();
        }
    }


    /**
     * 获取当前选择文本字符
     */
    public String getCurrentTextStr() {
        String currentTextStr = "";
        if (level > 0 && arrayLists[0].size() > 0) {
            currentTextStr = arrayLists[0].get(currentFirst);
        }
        if (level > 1 && arrayLists[1].size() > 0) {
            currentTextStr += "-" + arrayLists[1].get(currentSecond);
        }
        if (level > 2 && arrayLists[2].size() > 0) {
            currentTextStr += "-" + arrayLists[2].get(currentThird);
        }
        if (level > 3 && arrayLists[3].size() > 0) {
            currentTextStr += "-" + arrayLists[3].get(currentFourth);
        }
        if (level > 4 && arrayLists[4].size() > 0) {
            currentTextStr += "-" + arrayLists[4].get(currentFifth);
        }
        return currentTextStr;
    }
}
