package me.xueyao.util;

import me.xueyao.aes.SHA1;
import me.xueyao.config.PubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 微信公众号签名工具类
 *
 * @author Simon.Xue
 * @date 2020-03-03 11:56
 **/
@Component
public class SignHelper {

    @Autowired
    private PubConfig pubConfig;

    /**
     * 校验签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    public boolean validate(String signature, String timestamp, String nonce, String echostr) {
        String sortStr = sort(pubConfig.getToken(), timestamp, nonce);
        String sha1 = SHA1.getSHA1(sortStr);
        if (signature.equals(sha1)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 参数排序
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    private String sort(String token, String timestamp, String nonce) {
        String[] strArr = {token, timestamp, nonce};
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }
}
