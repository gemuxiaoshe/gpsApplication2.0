# gpsApplication2.0
GPS定位服务的小练习,显示当前定位信息

* **前言**
这是一个android gps定位服务的练习小项目.

    * 其功能:使用gps定位服务,获取并输出你的定位信息.

    * **演示:**

![2019-4-15-01.gif](https://upload-images.jianshu.io/upload_images/8067684-373bf6f1a5ba1169.gif?imageMogr2/auto-orient/strip)


**如需fork可以更改的地方:**


* **修改背景图片**

![image.png](https://upload-images.jianshu.io/upload_images/8067684-7f71bf5385880201.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


导入项目后,打开对应的xml文件.在源代码中找到对应的背景设置代码,将gps更改为你自己的背景图片,前提是你已经将背景图片复制到drawble目录下:


![image.png](https://upload-images.jianshu.io/upload_images/8067684-116de5ed387be259.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](https://upload-images.jianshu.io/upload_images/8067684-977c75474ca68036.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


* **将个人信息修改为自己的信息**

在xml文件中找到相对应的textview:

```
   <TextView
        android:id="@+id/tv_8"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="40dp"
        android:layout_marginLeft="60dp"
        android:padding="5dp"
        android:text="power by 小舍先生\u00A9计科5161马雅鹏"
        android:textColor="#fff"
        android:textSize="15dp"
        android:textStyle="bold" />
```

将其中的:

```
 android:text="power by 小舍先生\u00A9计科5161马雅鹏"
```

一行中的信息改为你自己的信息.

其他的就没有什么需要修改的地方了.

***
更新时间:
2019-4-10
1:36

