package me.xueyao.util;

/**
 * 通用工具类
 * @author Simon.Xue
 * @date 2020-03-20 16:44
 **/
public class CommonUtil {
    /**
     * 获得时间戳
     * @return
     */
    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 处理链接.html后的无用数据
     * @param moreUrl
     * @return 物料地址
     */
    public static String getJdkUrl(String moreUrl) {
        int index = moreUrl.indexOf(".html");
        if (-1 == index) {
            return null;
        }
        String substring = moreUrl.substring(0,index + 5);
        return substring;
    }

    /**
     * 判断连接是否是京东链接
     * @param url
     * @return true是京东链接  false不是京东链接
     */
    public static boolean isJdUrl(String url) {
        int indexOf = url.indexOf("jd.com");
        if (-1 != indexOf) {
            return true;
        } else {
            return false;
        }
    }
}
