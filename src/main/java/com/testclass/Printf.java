package com.testclass;

import java.util.Date;

/**
 * @Description: java在控制台的格式化输出
 * @Author: gaoxi
 * @CreateDate: 2019/7/23 16:36
 * @Version: 1.0
 * https://blog.csdn.net/crazyjmonkey/article/details/8537022
 */
public class Printf {
    /**
     * 演示java中在控制台的格式化输出
     * @author LiMing
     * @since 2012/01/24
     * */
    public static void main(String[] args) {

        /**
         * 常用标识符
         * %b 布尔值
         * %d 十进制
         * %c 字符
         * %s 字符串
         * %f 浮点数
         * %e 标准科学计数法
         * */

        System.out.printf("%s","LiMing");

        /**
         *%n 表示换行  亦可以使用System.out.println()
         *%S 将字符串以大写形式输出
         *支持多参数输出
         *多参数输出时例如
         *System.out.printf(format,item1,item2,item3...); format 是指一个字串以及格式标识符构成的字符串
         *条目必须与format中指定的格式标识符对应
         **/
        System.out.printf("%n%s%n", "end line");
        System.out.printf("%s = %s%n", "Name", "Zhangsan");
        System.out.printf("%S = %s%n", "Name", "Zhangsan");

        // 支持多个参数时，可以在%s之间插入变量编号，1$表示第一个字符串，3$表示第3个字符串
        System.out.printf("%1$s = %3$s %2$s%n", "Name", "san", "Zhang");
        System.out.printf("true = %b; false = ", true);
        System.out.printf("%b%n", false);

        /**@整数的格式化输出
         * %d表示将整数格式化为10进制整数
         * %o表示将整数格式化为8进制整数
         * %x表示将整数格式化为16进制整数
         * %X表示将整数格式化为16进制整数，并且字母变成大写形式
         * */
        Integer iObj=100;
        System.out.printf("%d;%d;%d%n", -100, 1234L, iObj);
        System.out.printf("%o; %o; %o%n", -100, 1234L, iObj);
        System.out.printf("%x; %x; %x%n", -100, 1234L, iObj);
        System.out.printf("%X; %X; %X%n", -100, 1234L, iObj);

        /**@浮点数格式化输出
         * %e表示以科学技术法输出浮点数
         * %E表示以科学技术法输出浮点数，并且为大写形式
         * %f表示以十进制格式化输出浮点数,还可以限制小数点后的位数
         * %10.2f 其中10代表域宽度，即输出字符所占的宽度;2表示精度，即小数点之后的位数;f表示格式描述符
         * */

        Double dObj = 12.34;
        System.out.printf("%e; %e; %e%n", -123.456f, 7464.232641d, dObj);
        System.out.printf("%E; %E; %E%n", -123.456f, 7464.232641d, dObj);
        System.out.printf("%f; %f; %f%n", -123.456f, 7464.232641d, dObj);
        System.out.printf("%.1f; %.3f; %f%n", -123.456f, 7464.232641d, dObj);
        System.out.printf("%10.2f;%5.3f%n%n", 123.4,123.4);


        /**
         * @时间的格式化输出
         * %t 表示格式化日期时间类型，%T是时间日期的大写形式，在%t之后用特定的字母表示不同的输出格式
         * %t之后用y表示输出日期的年份（2位数的年，如99）
         * %t之后用m表示输出日期的月份，
         * %t之后用d表示输出日期的日号
         * %t之后用Y表示输出日期的年份（4位数的年），
         * %t之后用B表示输出日期的月份的完整名， %t之后用b表示输出日期的月份的简称
         * %t之后用D表示以 "%tm/%td/%ty"格式化日期
         * %t之后用F表示以"%tY-%tm-%td"格式化日期
         * %t之后用H表示输出时间的时（24进制），%t之后用I表示输出时间的时（12进制）
         * %t之后用M表示输出时间的分，%t之后用S表示输出时间的秒
         * %t之后用L表示输出时间的秒中的毫秒
         * %t之后p表示输出时间的上午或下午信息
         * %t之后用R表示以"%tH:%tM"格式化时间
         * %t之后用T表示以"%tH:%tM:%tS"格式化时间
         * %t之后用r表示以"%tI:%tM:%tS %Tp"格式化时间
         * %t之后用A表示得到星期几的全称
         * %t之后用a表示得到星期几的简称
         * */
        Date date = new Date();
        long dataL = date.getTime();
        System.out.printf("%1$ty-%1$tm-%1$td%n", date);
        System.out.printf("%1$td-%1$tm-%1$ty%n", date);
        System.out.printf("%1$td-%1$tm-%1$ty%n", date);
        System.out.printf("%1$tY-%1$tB-%1$td%n", date);

        System.out.printf("%1$tD%n", date);
        System.out.printf("%1$tF%n", date);
        System.out.printf("%1$tH:%1$tM:%1$tS; %2$tI:%2$tM:%2$tS%n", date, dataL);
        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL%n", date);
        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL %1$tp%n", date);
        System.out.printf("%1$tR%n", date);
        System.out.printf("%1$tT%n", date);
        System.out.printf("%1$tr%n", date);
        System.out.printf("%1$tF %1$tA%n", date);
        System.out.printf("%1$tF %1$ta%n", date);
        System.out.printf("%1$tc%n", date);
    }

}