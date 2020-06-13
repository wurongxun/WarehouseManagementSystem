package com.shiro.test.mvc.base.tool;

/**
 * @Description StringUtil
 * @Author Rongxun.Wu
 * @Date 2020/2/11 14:22
 * @Version 1.0
 */
public class StringUtil {
    public static final String EMPTY = "";
    public static final String NULL = null;
    public static final String COMMA = ",";
    public static final String DOAT = ".";
    public static final String SEMICOLON = ";";
    public static final String COLON = ":";
    public static final String LEFT_SLASH = "/";
    public static final String RIGHT_SLASH = "\\";
    public static final String UNDER_LINE = "_";
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String USER_NAME = System.getProperty("user.name");
    public static final String USER_HOME = System.getProperty("user.home");
    public static final String USER_DIR = System.getProperty("user.dir");

    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEquals(String str1, String str2) {
        return str1 != null && str1.equals(str2);
    }

    public static boolean isNotEquals(String str1, String str2) {
        return !isEquals(str1, str2);
    }
}

