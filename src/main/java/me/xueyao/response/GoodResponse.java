package me.xueyao.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品响应
 * @author Simon.Xue
 * @date 2020-03-18 09:50
 **/
@Data
public class GoodResponse implements Serializable {
    /**
     * 商品标题
     */
    private String title;

    /**
     * 店铺名称
     */
    private String shopTitle;

    /**
     * 优惠券满减信息
     */
    //private String couponInfo;

    /**
     * 优惠券剩余量
     */
    //private Long couponRemainCount;

    /**
     * 链接-宝贝+券二合一页面链接
     */
    private String couponShareUrl;

    /**
     * 链接-宝贝推广链接
     */
    private String url;

    /**
     * 原价
     */
    private String zkFinalPrice;

    /**
     * 优惠券金额
     */
    private String couponAmount;

}
