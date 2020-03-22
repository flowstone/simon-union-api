package me.xueyao.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.xueyao.base.R;
import me.xueyao.config.PubConfig;
import me.xueyao.constant.WxConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon.Xue
 * @date 2020-03-03 19:44
 **/
@RestController
@RequestMapping("/base")
@Slf4j
public class WxBaseController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PubConfig pubConfig;

    /**
     * 获得唯一接口凭证
     * @return
     */
    @GetMapping("/getAccessToken")
    public R<String> getAccessToken() {
        Map<String, String> map = new HashMap<>(16);
        map.put(WxConstant.APP_ID, pubConfig.getAppId());
        map.put(WxConstant.APP_SECRET, pubConfig.getAppSecret());

        String body = restTemplate.getForEntity(pubConfig.getAccessTokenUrl(),
                String.class, map).getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        String accessToken = jsonObject.getString(WxConstant.ACCESS_TOKEN);
        if (null == accessToken) {
            log.warn("获取accessToken失败");
            return R.ofSystem("获取accessToken失败");
        }

        log.info("获取accessToken成功，accessToken = {}", accessToken);
        return R.ofSuccess("获取accessToken成功", accessToken);
    }


}
