package me.xueyao.controller;

import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import me.xueyao.constant.WxConstant;
import me.xueyao.enums.WxEnum;
import me.xueyao.response.GoodResponse;
import me.xueyao.util.SignHelper;
import me.xueyao.util.TbkUtil;
import me.xueyao.util.WxXmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Simon.Xue
 * @date 2020-03-03 10:38
 **/
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxWelcomeController {

    @Autowired
    private SignHelper signHelper;
    @Autowired
    private TbkUtil tbkUtil;

    @GetMapping("/tokenSign")
    public String validateSign(@RequestParam("signature") String signature,
                               @RequestParam("timestamp") String timestamp,
                               @RequestParam("nonce") String nonce,
                               @RequestParam("echostr") String echostr) {
        log.info("signature = {}, timestamp = {}， nonce = {}， echostr = {}", signature, timestamp, nonce, echostr);
        if (signHelper.validate(signature, timestamp, nonce, echostr)) {
            log.info("校验签名成功，echostr= {}", echostr);
            return echostr;
        } else {
            return null;
        }
    }


    @PostMapping("/tokenSign")
    public void receiveMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> map = WxXmlUtil.streamToMap(inputStream);
        String resultXml = handleMessage(map);

        WxXmlUtil.writeToResponse(response.getOutputStream(), resultXml);
    }

    /**
     * 处理各种类型信息
     * @param map
     * @return
     * @throws Exception
     */
    public String handleMessage(Map<String, String> map) throws Exception {
        String resultXmlStr = null;
        String msgType = map.get(WxConstant.MSG_TYPE);
        switch (msgType) {
            case "text":
                resultXmlStr = textMessage(map);
                break;
            case "news":
                break;
            case "event":
                resultXmlStr = subscribeMessage(map);
                break;
            default:
                map.put(WxConstant.CONTENT, "该信息类型不支持");
                resultXmlStr = textMessage(map);

        }
        return resultXmlStr;
    }

    /**
     * 处理文本信息
     * @param map
     * @return
     * @throws Exception
     */
    public String textMessage(Map<String, String> map) throws Exception {
        Map<String, String> resultMap = handleBaseMessage(map);
        resultMap.put(WxConstant.MSG_TYPE, WxEnum.MsgType.TEXT.getMsg());
        goodInfo(resultMap, map.get(WxConstant.CONTENT));
        return WxXmlUtil.mapToXml(resultMap);
    }

    public void goodInfo(Map<String, String> resultMap, String content) {
        List<GoodResponse> goodList = null;
        try {
            goodList = tbkUtil.getGoodList(content);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        if (goodList.isEmpty() || goodList.size() == 0) {
            resultMap.put(WxConstant.CONTENT, "");
        } else {
            StringBuilder sb = new StringBuilder();
            for (GoodResponse goodResponse : goodList) {
                String goodInfo = null;
                if (null == goodResponse.getCouponAmount()) {
                    goodInfo = String.format(WxConstant.NORMAL_FORMAT, goodResponse.getTitle(),
                            goodResponse.getZkFinalPrice(), goodResponse.getUrl());
                } else {
                    BigDecimal result = new BigDecimal(goodResponse.getZkFinalPrice())
                            .subtract(new BigDecimal(goodResponse.getCouponAmount()));

                    goodInfo = String.format(WxConstant.COUPON_FORMAT, goodResponse.getTitle(), goodResponse.getZkFinalPrice(),
                            result, goodResponse.getCouponShareUrl(), goodResponse.getUrl());
                }
                goodInfo += WxConstant.LINE;
                sb.append(goodInfo);
            }

            resultMap.put(WxConstant.CONTENT, sb.toString());
        }
    }

    /**
     * 处理订阅事件
     * @param map
     * @return
     * @throws Exception
     */
    public String subscribeMessage(Map<String, String> map) throws Exception {
        Map<String, String> resultMap = handleBaseMessage(map);
        resultMap.put(WxConstant.MSG_TYPE, WxEnum.MsgType.TEXT.getMsg());
        if (WxEnum.Event.subscribe.getMsg().equals(map.get(WxConstant.EVENT))) {
            resultMap.put(WxConstant.CONTENT, WxConstant.WELCOME);
        }
        return WxXmlUtil.mapToXml(resultMap);
    }

    /**
     * 处理共有的部分
     * @param map
     * @return
     */
    public Map<String, String> handleBaseMessage(Map<String, String> map) {
        Map<String, String> resultMap = new HashMap<>(16);
        resultMap.put(WxConstant.TO_USER_NAME, map.get(WxConstant.FROM_USER_NAME));
        resultMap.put(WxConstant.FROM_USER_NAME, map.get(WxConstant.TO_USER_NAME));
        resultMap.put(WxConstant.CREATE_TIME, map.get(WxConstant.CREATE_TIME));
        return resultMap;
    }
}
