package com.sishuok.es.tool;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-20 上午10:08
 * <p>Version: 1.0
 */
public class GenUtil {

    /**
     * 功能：将输入字符串的首字母,下划线后的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String initcap(String str) {
        return init_Cap(initFCap(str));
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String initFCap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：将输入字符串下划线后的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String init_Cap(String str) {
        String result = str;

        char[] ch = str.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '_') {
                ch[i + 1] = (char) (ch[i + 1] - 32);
            }
        }

        result = new String(ch);

        result = result.replaceAll("\\_", "");

        return result;
    }

}
