# AndroidWheelView
  更加随意组合的滚轮选择控件
## 通过Gradle下载依赖包

```groovy

dependencies {
    compile 'com.github.dusunboy:androidwheelview-library:1.0.2'
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

## 更新日志
* 1.0.2
    增加时间选择<br>
    常用城市区三级联动<br>
    自定义二，三级联动<br>
    自定义一，二，三，四，五无联动选择


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
