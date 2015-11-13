# AndroidWheelView
  更加随意组合的滚轮选择控件
  
## 演示
  ![demo](https://github.com/dusunboy/AndroidWheelView/blob/master/images/demo.gif?raw=true)
  
## 通过Gradle下载依赖包
```groovy

dependencies {
    compile 'com.github.dusunboy:androidwheelview-library:1.0.3'
    compile "com.google.code.gson:gson:2.3.1"
}

```
### 使用
---

1.实例化
```java
    WheelViewDialog wheelViewDialog = new WheelViewDialog(MainActivity.this, WheelViewDialog.DATE);
```


2.可选模式
>```
  日期 DATE
  日期加时间 DATE_TIME
  省市区三级联动 PROVINCE_CITY_AREA
  二级联动 TWO_LINKAGE
  三级联动 THREE_LINKAGE
  一级选择 ONE_LEVEL
  二级选择 TWO_LEVEL
  三级选择 THREE_LEVEL
  四级选择 FOUR_LEVEL
  五级选择 FIVE_LEVEL

>```

3.填充数据
```java
    //WheelViewDialog.DATE 日期
    wheelViewDialog.setCurrentDate("2015-11-11");
    //WheelViewDialog.DATE_TIME 日期加时间
    wheelViewDialog.setCurrentDate("2015-11-11");
    //WheelViewDialog.TWO_LINKAGE 二级联动
    //WheelViewDialog.THREE_LINKAGE 三级联动
    ArrayList<AreaBean> areaBeans = new ArrayList<AreaBean>();
    wheelViewDialog.setData(areaBeans);
    //----------------------无联动选择--------------------------

    //WheelViewDialog.ONE_LEVEL 一级选择
    //WheelViewDialog.TWO_LEVEL 二级选择
    //WheelViewDialog.THREE_LEVEL 三级选择
    //WheelViewDialog.FOUR_LEVEL 四级选择
    //WheelViewDialog.FIVE_LEVEL 五级选择
    ArrayList<String> oneArrays = new ArrayList<String>();
                        ...
    ArrayList<String> fiveArrays = new ArrayList<String>();
    //多参数传递
    wheelViewDialog.setData(oneArrays, ..., fiveArrays);
```

4.选择监听
```java
    //WheelViewDialog.DATE 日期, WheelViewDialog.DATE_TIME 日期加时间
    wheelViewDialog.setPositiveButton("确认", new OnDateSetListener() {
        @Override
        public void onDateSet(WheelViewDialog wheelViewDialog, int currentYear, int currentMonth, int currentDay,
                              int currentHour, int currentMinutes) {
            wheelViewDialog.dismiss();
            StringBuffer stringBuffer = new StringBuffer().append(currentYear).append("-")
            .append(currentMonth).append("-").append(currentDay).append("-")
                    .append(currentHour).append("-").append(currentMinutes);
            Toast.makeText(MainActivity.this,
                    stringBuffer.toString(),
                    Toast.LENGTH_LONG).show();
        }
    });

    //WheelViewDialog.TWO_LINKAGE 二级联动,WheelViewDialog.THREE_LINKAGE 三级联动
    //WheelViewDialog.ONE_LEVEL 一级选择,WheelViewDialog.TWO_LEVEL 二级选择,WheelViewDialog.THREE_LEVEL 三级选择,WheelViewDialog.FOUR_LEVEL 四级选择,WheelViewDialog.FIVE_LEVEL 五级选择
    wheelViewDialog.setPositiveButton("确认", new OnTextSetListener() {
        @Override
        public void onTextSet(WheelViewDialog wheelViewDialog, String text) {
            wheelViewDialog.dismiss();
            Toast.makeText(MainActivity.this,
                    text,
                    Toast.LENGTH_LONG).show();
        }
    });
    wheelViewDialog.setNegativeButton("取消", null);
```

5.设置标题
```java
    wheelViewDialog.setTitle("标题");
```
6.显示提示框
```java
    wheelViewDialog.show();
```

## 更新日志
* 1.0.3<br>
    移动library里省市区的数据到app里<br>
    README.md添加使用说明

* 1.0.2<br>
    增加时间日期选择<br>
    常用城市区三级联动<br>
    自定义二，三级联动<br>
    自定义一，二，三，四，五级无联动选择


## 感谢([ywl5320](http://blog.csdn.net/ywl5320/article/details/44730457))
-------
基于ywl5320作者的源码代码开发优化 
http://blog.csdn.net/ywl5320/article/details/44730457<br>

## License

    The MIT License (MIT)
    
    Copyright (c) [2015] [dusunboy]
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
