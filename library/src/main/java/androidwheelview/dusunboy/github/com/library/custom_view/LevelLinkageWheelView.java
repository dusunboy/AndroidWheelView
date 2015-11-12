package androidwheelview.dusunboy.github.com.library.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidwheelview.dusunboy.github.com.library.R;
import androidwheelview.dusunboy.github.com.library.adapters.WheelTextAdapter;
import androidwheelview.dusunboy.github.com.library.dialog.WheelViewDialog;
import androidwheelview.dusunboy.github.com.library.model.AreaBean;
import androidwheelview.dusunboy.github.com.library.util.DensityUtil;
import androidwheelview.dusunboy.github.com.library.views.OnWheelChangedListener;
import androidwheelview.dusunboy.github.com.library.views.OnWheelScrollListener;
import androidwheelview.dusunboy.github.com.library.views.WheelView;

/**
 * Created by Win8 on 2015/11/9.
 */
public class LevelLinkageWheelView extends LinearLayout implements OnWheelChangedListener, OnWheelScrollListener {

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

    private List<AreaBean> areaBeans;

    public LevelLinkageWheelView(Context context, int mode) {
        super(context);
        this.context = context;
        this.mode = mode;
        if (mode == WheelViewDialog.PROVINCE_CITY_AREA) {
            level = 3;
        } else if (mode == WheelViewDialog.TWO_LINKAGE) {
            level = 2;
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
            maxTextSize = 20;
            minTextSize = 12;
        }
        visibleItems = 5;

        LayoutParams liLayoutParams = new LayoutParams(DensityUtil.dp2px(context, viewWidth),
                DensityUtil.dp2px(context, 160));
        liLayoutParams.setMargins(DensityUtil.dp2px(context, 10), 0, DensityUtil.dp2px(context, 10), 0);
        li.setLayoutParams(liLayoutParams);


        if (mode == WheelViewDialog.PROVINCE_CITY_AREA) {
            LayoutParams wheelViewLayoutParams = new LayoutParams(DensityUtil.dp2px(context, (viewWidth) / 3),
                    LayoutParams.MATCH_PARENT);
            wv_first.setLayoutParams(wheelViewLayoutParams);
            wv_second.setLayoutParams(wheelViewLayoutParams);
            wv_third.setLayoutParams(wheelViewLayoutParams);
        } else if ( mode == WheelViewDialog.TWO_LINKAGE) {
            LayoutParams wheelViewLayoutParams = new LayoutParams(DensityUtil.dp2px(context, (viewWidth) / 2),
                    LayoutParams.MATCH_PARENT);
            wv_first.setLayoutParams(wheelViewLayoutParams);
            wv_second.setLayoutParams(wheelViewLayoutParams);
        }

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

        if (mode == WheelViewDialog.PROVINCE_CITY_AREA) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                InputStream inputStream = context.getAssets().open("city.json");
                int len = -1;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf)) != -1) {
                    stringBuffer.append(new String(buf, 0, len, "utf-8"));
                }
                inputStream.close();
                List<AreaBean> areaBeans = new Gson().fromJson(stringBuffer.toString(), new TypeToken<List<AreaBean>>(){}.getType());
                setData(areaBeans);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置级联数据
     * @param areaBeans
     */
    public void setData(List<AreaBean> areaBeans) {

        currentFirst = 0;
        currentSecond = 0;
        currentThird = 0;
        currentFourth = 0;
        currentFifth = 0;
        this.areaBeans = areaBeans;
        resetLevelData();
    }

    /**
     * 获取各个级联的数据
     */
    private void resetLevelData() {
        if (areaBeans != null) {
            firstAdapter.clear();
            secondAdapter.clear();
            thirdAdapter.clear();
            fourthAdapter.clear();
            fifthAdapter.clear();

            for (int i = 0; i < areaBeans.size(); i++) {
                firstAdapter.add(areaBeans.get(i).getName());
                if (i == currentFirst) {
                    List<AreaBean> secondAreaBeans = areaBeans.get(currentFirst).getArea();
                    for (int i1 = 0; i1 < secondAreaBeans.size(); i1++) {
                        secondAdapter.add(secondAreaBeans.get(i1).getName());
                        if (i1 == currentSecond) {
                            List<AreaBean> thirdAreaBeans = secondAreaBeans.get(currentSecond).getArea();
                            for (int i2 = 0; i2 < thirdAreaBeans.size(); i2++) {
                                thirdAdapter.add(thirdAreaBeans.get(i2).getName());
                                if (i2 == currentThird) {
                                    List<AreaBean> fourthAreaBeans = thirdAreaBeans.get(currentThird).getArea();
                                    for (int i3 = 0; i3 < fourthAreaBeans.size(); i3++) {
                                        fourthAdapter.add(fourthAreaBeans.get(i3).getName());
                                        if (i3 == currentFourth) {
                                            List<AreaBean> fifthAreaBeans = thirdAreaBeans.get(currentFourth).getArea();
                                            for (int i4 = 0; i4 < fifthAreaBeans.size(); i4++) {
                                                fifthAdapter.add(fifthAreaBeans.get(i4).getName());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
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

            currentSecond = 0;
            currentThird = 0;
            resetLevelData();
        } else if (wheel.getId() == R.id.wv_second) {
            currentSecond = wheel.getCurrentItem();
            secondAdapter.setCurrentIndex(wheel.getCurrentItem());

            currentThird = 0;
            if (level > 2) {
                resetLevelData();
            }
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
        String firstStr = "";
        String secondStr = "";
        String thirdStr = "";
        String fourthStr = "";
        String fifthStr = "";
        if (areaBeans.size() > 0) {
            firstStr = areaBeans.get(currentFirst).getName();
            List<AreaBean> secondArea = areaBeans.get(currentFirst).getArea();
            if (secondArea.size() > 0) {
                secondStr = secondArea.get(currentSecond).getName();
                List<AreaBean> thirdArea = secondArea.get(currentSecond).getArea();
                if (thirdArea.size() > 0) {
                    thirdStr = thirdArea.get(currentThird).getName();
                    List<AreaBean> fourthArea = thirdArea.get(currentThird).getArea();
                    if (fourthArea.size() > 0) {
                        fourthStr = fourthArea.get(currentFourth).getName();
                        List<AreaBean> fifthArea = fourthArea.get(currentFifth).getArea();
                        if (fifthArea.size() > 0) {
                            fifthStr = secondArea.get(currentFifth).getName();
                        }
                    }
                }
            }
        }
        return firstStr + "-" + secondStr + "-" + thirdStr + "-" + fourthStr + "-" + fifthStr;
    }
}
