package me.xueyao.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Simon.Xue
 * @date 2020-03-17 16:08
 **/
@Data
@Component
public class PubConfig {

    @Value("${wx.token}")
    private String token;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.encodingAESKey}")
    private String encodingAESKey;
    @Value("${url.wx.accessTokenUrl}")
    private String accessTokenUrl;
    @Value("${url.wx.createMenu}")
    private String createMenuUrl;

    /**
     * ---------淘宝客联盟--------
     */
    @Value("${taobao.mama.key}")
    private String mamaKey;
    @Value("${taobao.mama.secret}")
    private String mamaSecret;
    @Value("${taobao.mama.adzoneId}")
    private Long adzoneId;

    @Value("${url.taobao}")
    private String taobaoUrl;

    /**
     * -------京东联盟----------
     */
    @Value("${jd.union.key}")
    private String jdKey;
    @Value("${jd.union.secret}")
    private String jdSecret;
    @Value("${jd.union.siteId}")
    private String jdSiteId;
    @Value("${url.jd}")
    private String jdUrl;

}
