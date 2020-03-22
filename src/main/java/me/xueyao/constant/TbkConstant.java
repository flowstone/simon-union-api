package me.xueyao.constant;

/**
 * @author Simon.Xue
 * @date 2020-03-17 19:44
 **/
public class TbkConstant {
    /**
     * 商品筛选(特定媒体支持)-店铺dsr评分
     */
    public static final long startDsr = 10L;
    /**
     * 页大小，默认20，1~100
     */
    public static final long pageSize = 3L;
    /**
     * 第几页，默认：１
     */
    public static final long pageNo = 1L;
    /**
     * 链接形式：1：PC，2：无线，默认：１
     */
    public static final long platform = 1L;
    /**
     * 商品筛选-是否海外商品。true表示属于海外商品，false或不设置表示不限
     */
    public static final boolean isOverseas = false;
    /**
     * 商品筛选-是否天猫商品。true表示属于天猫商品，false或不设置表示不限
     */
    public static final boolean isTmall = false;
    /**
     * 排序_des（降序），排序_asc（升序），销量（total_sales），淘客佣金比率（tk_rate）， 累计推广量（tk_total_sales），总支出佣金（tk_total_commi），价格（price）
     */
    public static final String sort = "total_sales_des";
    /**
     * 优惠券筛选-是否有优惠券。true表示该商品有优惠券，false或不设置表示不限
     */
    public static final boolean hasCoupon = false;
    /**
     *商品筛选-是否包邮。true表示包邮，false或不设置表示不限
     */
    public static final boolean needFreeShipment = false;
    /**
     * 商品筛选-是否加入消费者保障。true表示加入，false或不设置表示不限
     */
    public static final boolean needPrepay = true;


}
