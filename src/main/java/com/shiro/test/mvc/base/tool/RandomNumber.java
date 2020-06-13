package com.shiro.test.mvc.base.tool;

/**
 * @Description sds
 * @Author Rongxun.Wu
 * @Date 2020/2/23 14:37
 * @Version 1.0
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class RandomNumber {
    // 使用单例模式，不允许直接创建实例
    private RandomNumber() {
    }

    // 创建一个空实例对象，类需要用的时候才赋值
    private static RandomNumber instance = null;

    // 单例模式--懒汉模式
    public static synchronized RandomNumber getInstance() {
        if (instance == null) {
            instance = new RandomNumber();
        }
        return instance;
    }

    // 全局自增数
    private static int count = 1;
    // 格式化的时间字符串
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MMdd-HHmm-");
    // 获取当前时间年月日时分秒毫秒字符串
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }
    private static String getNowDateStr2() {
        return sdf2.format(new Date());
    }

    // 记录上一次的时间，用来判断是否需要递增全局数
    private static String now = null;
    //定义锁对象
    private final static ReentrantLock lock = new ReentrantLock();

    //调用的方法
    public static String GetRandom() {
        String Newnumber = null;
        String dateStr = getNowDateStr();
        lock.lock();//加锁     	//判断是时间是否相同
        if (dateStr.equals(now)) {
            try {
                if (count >= 10000) {
                    count = 1;
                }
                if (count < 10) {
                    Newnumber = "N" + getNowDateStr() + "000" + count;
                } else if (count < 100) {
                    Newnumber = "N" + getNowDateStr() + "00" + count;
                } else if (count < 1000) {
                    Newnumber = "N" + getNowDateStr() + "0" + count;
                } else {
                    Newnumber = "N" + getNowDateStr() + count;
                }
                count++;
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        } else {
            count = 1;
            now = getNowDateStr();
            try {
                if (count >= 10000) {
                    count = 1;
                }
                if (count < 10) {
                    Newnumber = "N" + getNowDateStr() + "000" + count;
                } else if (count < 100) {
                    Newnumber = "N" + getNowDateStr() + "00" + count;
                } else if (count < 1000) {
                    Newnumber = "N" + getNowDateStr() + "0" + count;
                } else {
                    Newnumber = "N" + getNowDateStr() + count;
                }
                count++;
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
        return Newnumber;//返回的值
    }
    //调用的方法
    public static String GetRandom2() {
        String Newnumber = null;
        String dateStr = getNowDateStr2();
        lock.lock();//加锁     	//判断是时间是否相同
        if (dateStr.equals(now)) {
            try {
                if (count >= 10000) {
                    count = 1;
                }
                if (count < 10) {
                    Newnumber =getNowDateStr2() + "000" + count;
                } else if (count < 100) {
                    Newnumber =getNowDateStr2() + "00" + count;
                } else if (count < 1000) {
                    Newnumber =getNowDateStr2() + "0" + count;
                } else {
                    Newnumber =getNowDateStr2() + count;
                }
                count++;
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        } else {
            count = 1;
            now = getNowDateStr2();
            try {
                if (count >= 10000) {
                    count = 1;
                }
                if (count < 10) {
                    Newnumber =getNowDateStr2() + "000" + count;
                } else if (count < 100) {
                    Newnumber =getNowDateStr2() + "00" + count;
                } else if (count < 1000) {
                    Newnumber =getNowDateStr2() + "0" + count;
                } else {
                    Newnumber =getNowDateStr2() + count;
                }
                count++;
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
        return Newnumber;//返回的值
    }
}