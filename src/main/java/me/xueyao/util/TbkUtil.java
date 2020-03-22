package me.xueyao.util;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkSpreadGetRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkSpreadGetResponse;
import lombok.extern.slf4j.Slf4j;
import me.xueyao.config.PubConfig;
import me.xueyao.constant.TbkConstant;
import me.xueyao.response.GoodResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Simon.Xue
 * @date 2020-03-17 20:48
 **/
@Slf4j
@Component
public class TbkUtil {
    @Autowired
    private PubConfig pubConfig;

    private TaobaoClient taobaoClient;

    @Bean
    public void init() {
        this.taobaoClient = new DefaultTaobaoClient(pubConfig.getTaobaoUrl(),
                pubConfig.getMamaKey(), pubConfig.getMamaSecret());
    }
    /**
     * 获得查询的商品列表
     * @param goodTitle
     * @return
     */
    public List<GoodResponse> getGoodList(String goodTitle) throws ApiException {
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(TbkConstant.pageSize);
        req.setPageNo(TbkConstant.pageNo);
        req.setIsOverseas(TbkConstant.isOverseas);
        req.setIsTmall(TbkConstant.isTmall);
        req.setSort(TbkConstant.sort);
        req.setQ(goodTitle);
        req.setHasCoupon(TbkConstant.hasCoupon);
        req.setAdzoneId(pubConfig.getAdzoneId());
        req.setNeedFreeShipment(TbkConstant.needFreeShipment);
        req.setNeedPrepay(TbkConstant.needPrepay);

        TbkDgMaterialOptionalResponse response = taobaoClient.execute(req);
        if (!StringUtils.isEmpty(response.getSubCode())) {
            return Collections.emptyList();
        }

        List<TbkDgMaterialOptionalResponse.MapData> resultList = response.getResultList();
        log.info("原始数据 = {}", JSONObject.toJSONString(resultList));
        List<GoodResponse> goodResponseList = resultList.stream().map(mapData -> {
            GoodResponse goodResponse = new GoodResponse();
            BeanUtils.copyProperties(mapData, goodResponse);
            goodResponse.setUrl(genShortUrl(mapData.getUrl()));
            goodResponse.setCouponShareUrl(genShortUrl(mapData.getCouponShareUrl()));
            return goodResponse;
        }).collect(Collectors.toList());
        log.info("查询的商品列表 = {}", JSONObject.toJSONString(goodResponseList));
        return goodResponseList;
    }


    public String genShortUrl(String longUrl) {
        longUrl = "http:" + longUrl;
        TbkSpreadGetRequest.TbkSpreadRequest tbkSpreadRequest = new TbkSpreadGetRequest.TbkSpreadRequest();
        tbkSpreadRequest.setUrl(longUrl);

        TbkSpreadGetRequest req = new TbkSpreadGetRequest();
        req.setRequests(Arrays.asList(tbkSpreadRequest));

        TbkSpreadGetResponse response = null;
        try {
            response = taobaoClient.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }

        if (!StringUtils.isEmpty(response.getSubCode())) {
            return null;
        }
        String content = response.getResults().get(0).getContent();
        log.info("生成的短链接 = {}", content);
        return content;
    }

}
