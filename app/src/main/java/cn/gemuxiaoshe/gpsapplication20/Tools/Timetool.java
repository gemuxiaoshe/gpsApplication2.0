package cn.gemuxiaoshe.gpsapplication20.Tools;


import java.util.Date;
import java.util.Locale;

/**
 * @outhor xiaoshe
 * @date 2019/4/5  - @time 1:22
 * 创建格式时间的工具类.
 * 之前学到的格式化日期时间,,而我们知道,日期时间的表示,我们需要经常使用到,所以这里抽出为静态方法,作为工具类来使用.
 */
public class Timetool {

    static Date date = new Date();  // 实例化一个静态的Date对象.

    //将时间按24小时制,分:秒格式输出
    public static void ShowTime_colon(){
        System.out.println(String.format("%tR",date));
    }
    //将时间按24小时制,以时:分:秒的格式完整输出
    public static void ShowAlltime_colon(){
        System.out.println(String.format("%tT",date));
    }
    //将时间按减号,以:年-月-日的格式输出.
    public static void Showdate_Minus(){
        System.out.println(String.format("%tF",date));
    }
    // 将时间按斜杠,以月/日/年的格式输出
    public static void Showdate_Slash(){
        System.out.println(String.format("%tD",date));
    }
    // 将年-月-日的格式的日期和时:分的格式的时间组合输出
    public static void SdateTime_mc(){
        System.out.println(String.format("%tF",date)+" "+String.format("%tR",date));
    }
    // 将年-月-日的格式的日期和时:分:秒的格式的时间组合输出
    public static String SdateAllTime_mc(){
        String str = String.format("%tF",date)+" "+String.format("%tT",date);
        return str;
    }
    //将月/日/年的格式的日期和时:分的格式的时间组合输出
    public static void Sdatetime_sc(){
        System.out.println(String.format("%tD",date)+" "+String.format("%tR",date));
    }

    //英文下的的星期几全称输出
    public static void ShowWeek_e(){
        System.out.println(String.format(Locale.ENGLISH,"%tA",date));
    }
    //输出中文星期几
    public static void ShowWeek(){
        System.out.println(String.format(Locale.CHINA,"%tA",date));
    }

    // 按固定格式输出: x年x月x日;x时x分;
    public static void Sdate_china(){
        String ayear = String.format("%tY",date);
        String amonth = String.format("%tm",date);
        String aday = String.format("%te",date);
        System.out.println(ayear+"年"+amonth+"月"+aday+"日");
    }
    //按固定格式输出:x时x分
    public static void Stime_china(){
        String ahour = String.format("%tH",date);
        String aminute = String.format("%tM",date);
        System.out.println(ahour+"时"+aminute+"分");
    }
}

