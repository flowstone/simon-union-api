package me.xueyao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.xueyao.base.R;
import me.xueyao.config.PubConfig;
import me.xueyao.enums.WxEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon.Xue
 * @date 2020-03-20 21:06
 **/
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController {
    @Autowired
    private PubConfig pubConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WxBaseController wxBase;

    @PostMapping("/create")
    public R create(@RequestBody JSONObject request) {
        R<String> accessTokenR = wxBase.getAccessToken();
        if (!accessTokenR.getSuccess()) {
            return accessTokenR;
        }

        Map<String, String> map = new HashMap<>(16);
        map.put("ACCESS_TOKEN", accessTokenR.getData());
        String resultStr = restTemplate.postForEntity(pubConfig.getCreateMenuUrl(),
                request, String.class, map).getBody();

        JSONObject resultJson = JSON.parseObject(resultStr);
        int errCode = resultJson.getIntValue(WxEnum.ResultCode.errCode.getMsg());
        if (errCode != 0) {
            log.warn("创建自定义菜单失败,{}", resultJson);
            return R.ofParam("创建自定义菜单失败");
        }

        log.info("创建自定义菜单成功，{}", resultJson);
        return R.ofSuccess("创建自定义菜单成功", resultJson);
    }
}
