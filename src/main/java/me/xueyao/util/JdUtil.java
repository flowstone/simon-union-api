package me.xueyao.util;

import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import jd.union.open.promotion.common.get.request.PromotionCodeReq;
import jd.union.open.promotion.common.get.request.UnionOpenPromotionCommonGetRequest;
import jd.union.open.promotion.common.get.response.UnionOpenPromotionCommonGetResponse;
import lombok.extern.slf4j.Slf4j;
import me.xueyao.base.R;
import me.xueyao.config.PubConfig;
import me.xueyao.enums.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 京东联盟工具类
 * @author Simon.Xue
 * @date 2020-03-20 16:57
 **/
@Component
@Slf4j
public class JdUtil {
    @Autowired
    private PubConfig pubConfig;

    /**
     * 获取通用推广链接
     * @param goodUrl 商品链接
     * @return
     * @throws JdException
     */
    public R<String> getClickUrl(String goodUrl) throws JdException {
        JdClient client = new DefaultJdClient(pubConfig.getJdUrl(), "",
                pubConfig.getJdKey(), pubConfig.getJdSecret());
        UnionOpenPromotionCommonGetRequest request = new UnionOpenPromotionCommonGetRequest();
        PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
        promotionCodeReq.setMaterialId(goodUrl);
        promotionCodeReq.setSiteId(pubConfig.getJdSiteId());
        request.setPromotionCodeReq(promotionCodeReq);
        UnionOpenPromotionCommonGetResponse response = client.execute(request);
        log.info("{}", JSONObject.toJSONString(response));
        if (!StatusEnum.SUCCESS.getCode().equals(response.getCode())) {
            return R.ofParam(response.getMessage());
        }
        return R.ofSuccess("获取成功", response.getData().getClickURL());
    }
}
