package me.xueyao.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Simon.Xue
 * @date 2020-03-17 23:49
 **/
public class UrlUtil {

    public static void main(String[] args) throws IOException {
        String url = "https://m.tb.cn/h.V43UO4t?sm=f69f55";
        System.out.println("访问地址:" + url);
        URL serverUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) serverUrl
                .openConnection();
        conn.setRequestMethod("GET");
        // 必须设置false，否则会自动redirect到Location的地址
        conn.setInstanceFollowRedirects(false);

        conn.addRequestProperty("Accept-Charset", "UTF-8;");
        conn.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
        conn.addRequestProperty("Referer", "http://zuidaima.com/");
        conn.connect();
        String location = conn.getHeaderField("Location");

        serverUrl = new URL(location);
        conn = (HttpURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");

        conn.addRequestProperty("Accept-Charset", "UTF-8;");
        conn.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
        conn.addRequestProperty("Referer", "http://zuidaima.com/");
        conn.connect();
        System.out.println("跳转地址:" + location);

    }
}
