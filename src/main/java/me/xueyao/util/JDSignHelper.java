package me.xueyao.util;

import me.xueyao.config.PubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

/**
 * 京东联盟签名工具
 * 未使用
 * @author Simon.Xue
 * @date 2020-03-20 16:27
 **/
@Component
public class JDSignHelper {

    @Autowired
    private PubConfig pubConfig;
    /**
     * 构建签名
     * @param method
     * @param paramJson
     * @return
     * @throws Exception
     */
    private String buildSign(String method, String paramJson) throws Exception {

        //第一步，按照顺序填充参数
        Map<String, String> map = new TreeMap();
        map.put("timestamp", CommonUtil.getTimestamp());
        map.put("v", "1.0");
        map.put("sign_method", "md5");
        map.put("format", "json");
        map.put("method", method);
        //param_json为空的时候需要写成 "{}"
        map.put("param_json", paramJson);
        map.put("access_token", "");
        map.put("app_key", pubConfig.getJdKey());

        StringBuilder sb = new StringBuilder(pubConfig.getJdSecret());

        //按照规则拼成字符串
        for (Map.Entry entry : map.entrySet()) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            //检测参数是否为空
            if (areNotEmpty(new String[]{name, value})) {
                sb.append(name).append(value);
            }
        }
        sb.append(pubConfig.getJdSecret());

        //MD5
        return md5(sb.toString());

    }

    public static String md5(String source) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(source.getBytes("utf-8"));
        return byte2hex(bytes);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();

    }

    public static boolean areNotEmpty(String[] values) {
        boolean result = true;
        if ((values == null) || (values.length == 0)) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }


    public static boolean isEmpty(String value) {
        int strLen;
        if ((value == null) || ((strLen = value.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
