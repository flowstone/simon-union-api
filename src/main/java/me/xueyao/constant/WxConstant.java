package me.xueyao.constant;

/**
 * @author Simon.Xue
 * @date 2020-02-29 17:24
 **/
public class WxConstant {
    public final static String MD5 = "MD5";
    public final static String SIGN = "sign";
    public final static String HMACSHA256 = "HMACSHA256";
    public final static String EVENT = "Event";

    public final static String APP_ID = "APPID";
    public final static String APP_SECRET = "APPSECRET";
    public final static String ACCESS_TOKEN = "access_token";

    public final static String MSG_TYPE = "MsgType";
    public final static String CONTENT = "Content";
    public final static String TO_USER_NAME = "ToUserName";
    public final static String FROM_USER_NAME = "FromUserName";
    public final static String CREATE_TIME = "CreateTime";

    public final static String WELCOME = "欢迎关注流体石头的分享店!\n" +
            "可以在本公众号领取商品优惠券" +
            "\n目前支持淘宝商品\n" +
            "\n----- 使用说明 -----\n" +
            "淘宝>请发送商品名称";


    /**
     * ------- 淘宝客 -------
     */
    /**
     * 有优惠的格式
     */
    public final static String COUPON_FORMAT = "%s\n----------------------------------\n" +
            "原价:%s元,券后价仅%s元" +
            "\n----------------------------------\n" +
            "【立即领券】点击链接即可领券购买：\n%s\n" +
            "【立即下单】点击链接立即下单：\n%s";
    /**
     * 正常的格式
     */
    public final static String NORMAL_FORMAT = "%s\n----------------------------------\n" +
            "【在售价】%s 元" +
            "\n----------------------------------\n" +
            "【立即下单】点击链接立即下单：\n%s";
    /**
     * 分割钱
     */
    public final static String LINE = "\n\n---------------------\n\n";


    /**
     * ------- 京东联盟 -------
     */
}
